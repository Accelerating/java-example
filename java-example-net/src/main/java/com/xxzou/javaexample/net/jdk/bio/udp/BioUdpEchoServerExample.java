package com.xxzou.javaexample.net.jdk.bio.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BioUdpEchoServerExample {
    private static final int PORT = 9999;

    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(PORT);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("receive data: " + sentence);
            String response = sentence.toUpperCase();

            sendData = response.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);

            receiveData = new byte[1024];
            sendData = new byte[1024];
        }
    }
}
