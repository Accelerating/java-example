package com.xxzou.javaexample.net.jdk.nio.udp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class NioUdpServerExample {

    public static void main(String[] args) throws Exception{
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress("127.0.0.1", 9999));

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);
        for (;;){
            if(selector.select(500) == 0){
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            if(keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                DatagramChannel datagramChannel = (DatagramChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                datagramChannel.receive(buffer);
                System.out.println(new String(buffer.array()));
                keyIterator.remove();
            }
        }
    }

}
