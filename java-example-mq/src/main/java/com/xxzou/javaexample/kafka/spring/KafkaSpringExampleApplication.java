package com.xxzou.javaexample.kafka.spring;

import com.xxzou.block.BlockUtils;
import com.xxzou.javaexample.kafka.spring.producer.KafkaProducerSpringExample;
import com.xxzou.random.RandNumUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaSpringExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(KafkaSpringExampleApplication.class, args);
        KafkaProducerSpringExample producer = ctx.getBean(KafkaProducerSpringExample.class);

        for (;;){
            String message = BlockUtils.waitForInput();
            String key = String.valueOf(RandNumUtils.getRandInt(1, 100));
            producer.send("test-topic", key, message);
        }

    }

}
