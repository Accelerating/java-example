package com.xxzou.javaexample.net.netty.udp.echo.server.handler;

import com.xxzou.javaexample.net.netty.udp.echo.entity.NettyUdpEchoMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class NettyUdpEchoServerHandler extends SimpleChannelInboundHandler<NettyUdpEchoMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyUdpEchoMessage msg) throws Exception {

        System.out.println(msg);

        String content = msg.getContent();
        String returnContent = "echo: " + content;
        NettyUdpEchoMessage returnMsg = new NettyUdpEchoMessage();
        returnMsg.setMsgId(System.currentTimeMillis());
        returnMsg.setContent(returnContent);
        returnMsg.setDest(msg.getSrc());
        ctx.writeAndFlush(returnMsg);
    }
}
