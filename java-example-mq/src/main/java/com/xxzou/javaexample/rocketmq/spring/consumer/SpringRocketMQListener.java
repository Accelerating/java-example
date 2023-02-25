package com.xxzou.javaexample.rocketmq.spring.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "TopicTest", consumerGroup = "test_group")
public class SpringRocketMQListener implements RocketMQListener<String> {


    @Override
    public void onMessage(String s) {
        System.out.println("接收消息:" + s);
    }
}
