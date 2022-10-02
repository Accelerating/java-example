package com.xxzou.javaexample.net.jdk.bio.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;

public class BioTcpServerExample {

    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 9999));
        for(;;){
            Socket socket = serverSocket.accept();
            new Thread(()->{
                try{
                    InputStream in = socket.getInputStream();
                    byte[] buf = new byte[1024];
                    int len = 0;
                    while((len = in.read(buf)) != -1){
                        System.out.println(new String(buf, 0, len));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }).start();
        }


    }

}
