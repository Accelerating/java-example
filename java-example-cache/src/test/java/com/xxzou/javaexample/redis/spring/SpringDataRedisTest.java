package com.xxzou.javaexample.redis.spring;


import com.xxzou.javaexample.redis.spring.basic.BasicUsageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SpringDataRedisTest {

    @Autowired
    private BasicUsageService basicUsageService;


    @Test
    public void test(){
        basicUsageService.set("hello", "world");
    }

}
