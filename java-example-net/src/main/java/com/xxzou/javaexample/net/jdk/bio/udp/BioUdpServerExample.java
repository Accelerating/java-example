package com.xxzou.javaexample.net.jdk.bio.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class BioUdpServerExample {

    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket(new InetSocketAddress("127.0.0.1", 9999));
        for(;;){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            ds.receive(packet);
            System.out.println(new String(data, 0, packet.getLength()));
        }

    }

}
