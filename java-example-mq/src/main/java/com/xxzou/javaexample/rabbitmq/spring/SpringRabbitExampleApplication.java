package com.xxzou.javaexample.rabbitmq.spring;

import com.xxzou.javaexample.utils.BlockUtils;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRabbitExampleApplication {

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new JsonMessageConverter();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringRabbitExampleApplication.class, args);
        SpringRabbitProducerExample producer = ctx.getBean(SpringRabbitProducerExample.class);
        while(true){
            BlockUtils.blockUntilInput();
            producer.send();
        }
    }

}
