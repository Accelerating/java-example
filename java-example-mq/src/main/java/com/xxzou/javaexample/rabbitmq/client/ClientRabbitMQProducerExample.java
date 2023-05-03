package com.xxzou.javaexample.rabbitmq.client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xxzou.javaexample.rabbitmq.entity.RabbitMessage;

import java.io.Closeable;

public class ClientRabbitMQProducerExample {

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        factory.setHost("192.168.3.23");
        factory.setPort(5672);

        Connection connection = null;
        Channel channel = null;
        try{
            connection = factory.newConnection();
            channel = connection.createChannel();
            String exchangeName = "test_exchange";
            String queueName = "test_queue";
            channel.exchangeDeclare(exchangeName, "direct", true);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, "test");

            RabbitMessage msg = RabbitMessage.randomMessage();
            channel.basicPublish(exchangeName, "test", null, msg.serialize());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(connection);
            close(channel);
        }
    }

    private static void close(AutoCloseable resourse){
        if(resourse != null){
            try{
                resourse.close();
            }catch (Exception ignore){}
        }
    }

}
