package com.xxzou.javaexample.kafka.spring.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerSpringExample {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerSpringExample.class);

    @KafkaListener(topics = "test-topic", groupId = "test-group", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(String message){
        log.info("收到消息:{}", message);
    }


}
