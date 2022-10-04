package com.xxzou.javaexample.net.netty.tcp.rpc.server;

import com.xxzou.javaexample.net.netty.tcp.rpc.codec.NettyRpcJsonCodec;
import com.xxzou.javaexample.net.netty.tcp.rpc.server.handler.NettyRpcServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyRpcServerExample {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            pipeline.addLast(new NettyRpcJsonCodec());
                            pipeline.addLast(new NettyRpcServerHandler());
                        }
                    });
            ChannelFuture cf = bootstrap.bind("127.0.0.1", 9999);
            cf.addListener((f)->{
                if(f.isSuccess()){
                    System.out.println("rpc server start...");
                }
            }).channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
