package com.xxzou.javaexample.net.jdk.nio.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTcpClientExample {

    public static void main(String[] args) throws Exception{
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);

        new Thread(()->{
            for (;;){
                try{
                    if(selector.select(1000) > 0){
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                        while(keyIterator.hasNext()){
                            SelectionKey key = keyIterator.next();
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            socketChannel.read(buffer);
                            System.out.println(new String(buffer.array()));
                            keyIterator.remove();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;){
            String msg = reader.readLine();
            channel.write(ByteBuffer.wrap(msg.getBytes()));
        }

    }

}
