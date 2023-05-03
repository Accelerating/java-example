package com.xxzou.javaexample.rabbitmq.client;

import com.rabbitmq.client.*;
import com.xxzou.javaexample.rabbitmq.entity.RabbitMessage;
import com.xxzou.javaexample.utils.BlockUtils;
import com.xxzou.javaexample.utils.JsonUtils;

import java.io.IOException;

import static org.springframework.amqp.rabbit.core.RabbitAdmin.QUEUE_NAME;

public class ClientRabbitMQConsumerExample {

    public static void main(String[] args) {

        // 获取到连接以及mq通道
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


            channel.basicConsume(queueName, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    RabbitMessage rabbitMessage = JsonUtils.toObject(body, RabbitMessage.class);
                    System.out.println(rabbitMessage);
                }
            });
            BlockUtils.blockUntilInput();
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
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
