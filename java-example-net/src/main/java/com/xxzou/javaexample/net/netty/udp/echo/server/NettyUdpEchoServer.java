package com.xxzou.javaexample.net.netty.udp.echo.server;

import com.xxzou.javaexample.net.netty.udp.echo.codec.NettyUdpEchoDecoder;
import com.xxzou.javaexample.net.netty.udp.echo.codec.NettyUdpEchoEncoder;
import com.xxzou.javaexample.net.netty.udp.echo.server.handler.NettyUdpEchoServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

public class NettyUdpEchoServer {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, false)
                    .handler(new ChannelInitializer<DatagramChannel>() {
                        @Override
                        protected void initChannel(DatagramChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyUdpEchoDecoder());
                            pipeline.addLast(new NettyUdpEchoEncoder());
                            pipeline.addLast(new NettyUdpEchoServerHandler());

                        }
                    });
            bootstrap.bind("127.0.0.1", 9999).addListener((future -> {
                if (future.isSuccess()) {
                    System.out.println("udp echo server start");
                }
            })).sync().channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }

}
