package com.xxzou.javaexample.redis;

import com.xxzou.javaexample.redis.service.RedisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RedisCacheExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(RedisCacheExampleApplication.class, args);
        RedisService redis = ctx.getBean(RedisService.class);
        redis.set("hello", "world");
        System.out.println(redis.get("hello"));
    }

}
