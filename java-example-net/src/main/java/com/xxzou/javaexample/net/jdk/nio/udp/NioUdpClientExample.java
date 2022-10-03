package com.xxzou.javaexample.net.jdk.nio.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class NioUdpClientExample {

    public static void main(String[] args) throws Exception{
        DatagramChannel channel = DatagramChannel.open();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(;;){
            String msg = reader.readLine();
            channel.send(ByteBuffer.wrap(msg.getBytes()), new InetSocketAddress("127.0.0.1", 9999));
        }

    }

}
