package com.xxzou.javaexample.net.netty.tcp.echo.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class NettyEchoStringCodec extends ByteToMessageCodec<String> {


    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        byte[] data = msg.getBytes();
        out.writeBytes(data);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readableBytes();
        if(length > 0){
            byte[] data = new byte[length];
            in.readBytes(data);
            out.add(new String(data));
        }
    }
}
