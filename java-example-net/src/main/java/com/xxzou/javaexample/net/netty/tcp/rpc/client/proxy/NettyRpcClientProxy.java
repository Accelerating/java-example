package com.xxzou.javaexample.net.netty.tcp.rpc.client.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxzou.javaexample.net.netty.tcp.rpc.codec.NettyRpcJsonCodec;
import com.xxzou.javaexample.net.netty.tcp.rpc.packet.NettyRpcMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NettyRpcClientProxy {

    private static NettyRpcClientProxy INSTANCE = new NettyRpcClientProxy();

    private Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    private Connection connection = new Connection();

    private NettyRpcClientProxy(){}

    public static void close(){
        INSTANCE.connection.close();
    }

    public static <T> T getService(Class<T> clazz){
        String className = clazz.getName();
        Object service = INSTANCE.serviceMap.computeIfAbsent(className, k -> INSTANCE.createProxy(clazz));
        return (T)service;
    }

    private <T> T createProxy(Class<T> clazz){
        if(!connection.isActive()){
            connection.connect("127.0.0.1", 9999);
        }
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RpcProxyHandler(connection));
    }

    static class RpcProxyHandler implements InvocationHandler{

        Connection connection;
        RpcProxyHandler(Connection connection){
            this.connection = connection;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Class<?> returnType = method.getReturnType();

            String interfaceName = method.getDeclaringClass().getSimpleName();
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            List<String> paramList = Stream.of(parameterTypes).map(Class::getSimpleName).collect(Collectors.toList());
            String service = interfaceName + "." + methodName + paramList;
            Object result = connection.invoke(service, args, returnType);
            return result;
        }
    }

    static class Connection extends SimpleChannelInboundHandler<NettyRpcMessage> {

        private static final int INACTIVE = 1;
        private static final int CONNECTING = 2;
        private static final int ACTIVE = 3;

        private Map<Long, Result> resultMap = new ConcurrentHashMap<>();
        private volatile AtomicInteger status = new AtomicInteger(INACTIVE);

        Bootstrap bootstrap;
        EventLoopGroup eventLoopGroup;
        String host;
        int port;
        ChannelHandlerContext ctx;

        public void connect(String host, int port){
            ChannelHandler clientHandler = this;
            if(status.compareAndSet(INACTIVE, CONNECTING)){
                bootstrap = new Bootstrap();
                eventLoopGroup = new NioEventLoopGroup();
                this.host = host;
                this.port = port;
                bootstrap.group(eventLoopGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast(new NettyRpcJsonCodec());
                                pipeline.addLast(clientHandler);
                            }
                        });
                try{

                    bootstrap.connect(host, port).sync();
                    status.compareAndSet(CONNECTING, ACTIVE);
                    System.out.println("connected...");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

        public void close(){
            eventLoopGroup.shutdownGracefully();
        }

        public boolean isActive(){
            return this.status.get() == ACTIVE;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            this.ctx = ctx;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, NettyRpcMessage msg) throws Exception {
            Long msgId = msg.getMsgId();
            byte[] data = msg.getResult();
            Result result = resultMap.get(msgId);
            result.setData(data);
        }

        public <T> T invoke(String service, Object[] args, Class<T> resultType){
            if(!isActive()){
                throw new RuntimeException("No Connection");
            }

            long msgId = System.currentTimeMillis();

            NettyRpcMessage msg = new NettyRpcMessage();
            msg.setMsgId(msgId);
            msg.setService(service);
            msg.setArgs(args);

            Result<T> result = new Result<>(resultMap, msgId, resultType);
            this.ctx.writeAndFlush(msg);
            return result.getData();
        }
    }

    static class Result<T>{
        static ObjectMapper mapper = new ObjectMapper();
        Lock lock;
        Condition condition;
        Class<T> resultType;
        volatile byte[] data;
        Long msgId;
        Map<Long, Result> resultMap;

        Result(Map<Long, Result> resultMap, Long msgId, Class<T> resultType){
            this.resultMap = resultMap;
            this.resultMap.put(msgId, this);
            this.resultType = resultType;
            this.lock = new ReentrantLock();
            this.msgId = msgId;
        }

        T getData(){
            lock.lock();
            try{
                this.condition = lock.newCondition();
                this.condition.await();
                if(this.data != null){
                    return mapper.readValue(this.data, resultType);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
//                this.resultMap.remove(this.msgId);
                lock.unlock();
            }
            return null;
        }

        void setData(byte[] data){
            lock.lock();
            try{
                this.data = data;
                this.condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }

    }

}
