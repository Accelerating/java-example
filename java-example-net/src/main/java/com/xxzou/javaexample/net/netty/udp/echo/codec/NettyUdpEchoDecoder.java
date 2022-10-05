package com.xxzou.javaexample.net.netty.udp.echo.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxzou.javaexample.net.netty.udp.echo.entity.NettyUdpEchoMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.net.InetSocketAddress;
import java.util.List;

public class NettyUdpEchoDecoder extends MessageToMessageDecoder<DatagramPacket> {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket packet, List<Object> out) throws Exception {
        ByteBuf buf = packet.content();
        int length = buf.readableBytes();
        byte[] data = new byte[length];
        buf.readBytes(data);

        InetSocketAddress sender = packet.sender();
        NettyUdpEchoMessage message = mapper.readValue(data, NettyUdpEchoMessage.class);
        message.setSrc(sender);
        out.add(message);

    }
}
