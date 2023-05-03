package com.xxzou.javaexample.kafka.spring.producer;


import com.xxzou.javaexample.kafka.spring.consumer.KafkaConsumerSpringExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerSpringExample {

    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaProducerSpringExample(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }


    public void send(String topic, String key, String data) {
        kafkaTemplate.send(topic, key, data);
    }
}
