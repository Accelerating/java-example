package com.xxzou.javaexample.redis.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SpringBootRedisExampleApplication {

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootRedisExampleApplication.class, args);
    }
}
