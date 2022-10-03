package com.xxzou.javaexample.net.netty.tcp.echo.client;

import com.xxzou.javaexample.net.netty.tcp.echo.codec.NettyEchoStringCodec;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NettyTcpEchoClient {

    public static void main(String[] args) throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        NettyTcpEchoClientHandler client = new NettyTcpEchoClientHandler();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new NettyEchoStringCodec());
                        pipeline.addLast(client);
                    }
                });
        bootstrap.connect("127.0.0.1", 9999).sync();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;){
            String msg = reader.readLine();
            client.send(msg);
        }

    }

}
