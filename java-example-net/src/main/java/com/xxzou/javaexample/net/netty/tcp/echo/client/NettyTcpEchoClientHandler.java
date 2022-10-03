package com.xxzou.javaexample.net.netty.tcp.echo.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyTcpEchoClientHandler extends SimpleChannelInboundHandler<String> {

    public ChannelHandlerContext context;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.context = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

    public void send(String msg){
        this.context.writeAndFlush(msg);
    }
}
