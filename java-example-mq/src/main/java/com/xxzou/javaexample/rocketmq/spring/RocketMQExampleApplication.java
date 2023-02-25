package com.xxzou.javaexample.rocketmq.spring;


import com.xxzou.javaexample.rocketmq.spring.producer.SpringRocketMQProducer;
import com.xxzou.javaexample.utils.BlockUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RocketMQExampleApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(RocketMQExampleApplication.class, args);
        SpringRocketMQProducer producer = ctx.getBean(SpringRocketMQProducer.class);
        while(true){
            String text = BlockUtils.blockUntilInput();
            producer.sendMessage(text);
        }
    }

}
