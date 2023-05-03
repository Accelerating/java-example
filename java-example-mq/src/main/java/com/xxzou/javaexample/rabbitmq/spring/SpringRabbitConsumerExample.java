package com.xxzou.javaexample.rabbitmq.spring;

import com.xxzou.javaexample.rabbitmq.entity.RabbitMessage;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitConsumerExample {

    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(value = "test_exchange"), value = @Queue(value = "test_queue"),key = "test"))
    public void consume(RabbitMessage message){

        System.out.println(message);

    }

}
