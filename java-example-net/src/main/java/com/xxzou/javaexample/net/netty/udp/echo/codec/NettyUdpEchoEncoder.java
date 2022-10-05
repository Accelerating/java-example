package com.xxzou.javaexample.net.netty.udp.echo.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxzou.javaexample.net.netty.udp.echo.entity.NettyUdpEchoMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.DatagramPacketEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.net.InetSocketAddress;
import java.util.List;

public class NettyUdpEchoEncoder extends MessageToMessageEncoder<NettyUdpEchoMessage>{

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyUdpEchoMessage msg, List<Object> out) throws Exception {
        byte[] bytes = mapper.writeValueAsBytes(msg);
        ByteBuf buffer = ctx.alloc().buffer(bytes.length);
        buffer.writeBytes(bytes);
        DatagramPacket packet = new DatagramPacket(buffer, msg.getDest());
        out.add(packet);
    }
}
