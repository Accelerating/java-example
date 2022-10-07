package com.xxzou.javaexample.redis.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootRedisExampleApplication {

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootRedisExampleApplication.class, args);
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
//        template.opsForValue().set("hello", "world");
//        template.opsForHash().put("hkey", "hfield", "hvalue");
//        ......


        //lua script
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL lua = classLoader.getResource("lua/incrByAndExpire.lua");
        Path scriptPath = Paths.get(lua.toURI());
        String scriptText = Files.readString(scriptPath);
        RedisScript<Long> script = RedisScript.of(scriptText, Long.class);
        List<String> keys = new ArrayList<>();
        keys.add("number");
        Object result = template.execute(script, keys, "2", "1000");
        System.out.println(result);
    }
}
