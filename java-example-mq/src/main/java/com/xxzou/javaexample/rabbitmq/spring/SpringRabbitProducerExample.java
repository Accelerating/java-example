package com.xxzou.javaexample.rabbitmq.spring;

import com.xxzou.javaexample.rabbitmq.entity.RabbitMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitProducerExample {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("test_exchange", "test", RabbitMessage.randomMessage());
    }

}
