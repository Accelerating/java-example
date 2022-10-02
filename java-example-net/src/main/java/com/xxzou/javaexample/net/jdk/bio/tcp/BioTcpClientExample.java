package com.xxzou.javaexample.net.jdk.bio.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BioTcpClientExample {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 9999));
        OutputStream out = socket.getOutputStream();
        for(;;){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            out.write(input.getBytes());
            out.flush();
        }
    }

}
