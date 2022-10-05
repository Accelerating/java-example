package com.xxzou.javaexample.net.netty.udp.echo.client;

import com.xxzou.javaexample.net.netty.udp.echo.client.handler.NettyUdpEchoClientHandler;
import com.xxzou.javaexample.net.netty.udp.echo.codec.NettyUdpEchoDecoder;
import com.xxzou.javaexample.net.netty.udp.echo.codec.NettyUdpEchoEncoder;
import com.xxzou.javaexample.net.netty.udp.echo.entity.NettyUdpEchoMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class NettyUdpEchoClient {

    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, false)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        protected void initChannel(NioDatagramChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyUdpEchoDecoder());
                            pipeline.addLast(new NettyUdpEchoEncoder());
                            pipeline.addLast(new NettyUdpEchoClientHandler());
                        }
                    });

            ChannelFuture f = bootstrap.bind("127.0.0.1", 10000).addListener((future -> {
                if (future.isSuccess()) {
                    System.out.println("udp echo client start");
                }
            })).sync();

            Channel channel = f.channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                String input = reader.readLine();
                NettyUdpEchoMessage message = new NettyUdpEchoMessage();
                message.setContent(input);
                message.setMsgId(System.currentTimeMillis());
                message.setDest(new InetSocketAddress("127.0.0.1", 9999));
                channel.writeAndFlush(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }


    }

}
