package com.xxzou.javaexample.net.jdk.bio.udp;

import java.net.*;

public class BioUdpEchoClientExample {
    private static final int PORT = 9999;

    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket(9998);

        InetAddress serverAddress = InetAddress.getByName("localhost");
        byte[] sendData = "hello, world!".getBytes();
        byte[] receiveData = new byte[1024];

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, PORT);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Response from server: " + response);

        clientSocket.close();
    }
}
