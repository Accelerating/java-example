package com.xxzou.javaexample.net.netty.tcp.rpc.server.handler;

import com.xxzou.javaexample.net.netty.tcp.rpc.packet.NettyRpcMessage;
import com.xxzou.javaexample.net.netty.tcp.rpc.server.ctx.ServiceExecutor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyRpcServerHandler extends SimpleChannelInboundHandler<NettyRpcMessage> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("new client...");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyRpcMessage msg) throws Exception {
        String service = msg.getService();
        Object[] args = msg.getArgs();
        Object result = ServiceExecutor.getInstance().executeService(service, args);
        System.out.println("invoke " + service);
        NettyRpcMessage returnMsg = new NettyRpcMessage();
        returnMsg.setData(result);
        returnMsg.setMsgId(msg.getMsgId());
        ctx.writeAndFlush(returnMsg);
    }
}
