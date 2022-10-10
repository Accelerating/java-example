package com.xxzou.javaexample.redis.springdata;

import com.xxzou.javaexample.redis.springdata.entity.UserInfo;
import com.xxzou.javaexample.redis.springdata.service.BasicUsageService;
import com.xxzou.javaexample.redis.springdata.service.LuaScriptService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class SpringBootRedisExampleApplication {

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootRedisExampleApplication.class, args);
        //block
        System.in.read();
    }
}
