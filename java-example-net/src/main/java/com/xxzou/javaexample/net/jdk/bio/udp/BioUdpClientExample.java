package com.xxzou.javaexample.net.jdk.bio.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class BioUdpClientExample {

    public static void main(String[] args) throws Exception{

        DatagramSocket socket = new DatagramSocket();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(;;){
            String msg = reader.readLine();
            byte[] data = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length);
            packet.setSocketAddress(new InetSocketAddress("127.0.0.1", 9999));
            socket.send(packet);
        }


    }

}
