package com.xxzou.javaexample.net.netty.tcp.rpc.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxzou.javaexample.net.netty.tcp.rpc.packet.NettyRpcMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class NettyRpcJsonCodec extends ByteToMessageCodec<NettyRpcMessage> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyRpcMessage msg, ByteBuf out) throws Exception {
        try{
            if(msg.getData() != null){
                byte[] dataBytes = mapper.writeValueAsBytes(msg.getData());
                msg.setResult(dataBytes);
            }
            byte[] data = mapper.writeValueAsBytes(msg);
            out.writeInt(data.length);
            out.writeBytes(data);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readInt();
        if(in.readableBytes() >= length){
            byte[] data = new byte[length];
            in.readBytes(data);
            NettyRpcMessage message = mapper.readValue(data, NettyRpcMessage.class);
            out.add(message);
        }
    }
}
