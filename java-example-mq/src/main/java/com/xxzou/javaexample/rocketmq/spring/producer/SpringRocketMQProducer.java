package com.xxzou.javaexample.rocketmq.spring.producer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpringRocketMQProducer {

    private RocketMQTemplate rocketMQTemplate;
    public SpringRocketMQProducer(RocketMQTemplate rocketMQTemplate){
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void sendMessage(String text){
        Map<String, Object> payload = new HashMap<>();
        payload.put("text", text);
        payload.put("time", System.currentTimeMillis());
        SendResult result = rocketMQTemplate.syncSend("TopicTest", payload);
        System.out.println(result);
    }
}
