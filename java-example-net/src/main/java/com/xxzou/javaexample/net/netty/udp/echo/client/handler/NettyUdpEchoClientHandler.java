package com.xxzou.javaexample.net.netty.udp.echo.client.handler;

import com.xxzou.javaexample.net.netty.udp.echo.entity.NettyUdpEchoMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class NettyUdpEchoClientHandler extends SimpleChannelInboundHandler<NettyUdpEchoMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyUdpEchoMessage msg) throws Exception {
        System.out.println(msg);
    }
}
