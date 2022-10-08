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
        BasicUsageService service = ctx.getBean(BasicUsageService.class);
        UserInfo user = new UserInfo();
        user.setId(1L);
        user.setName("jack");
        user.setCreateTime(new Date());
        user.setPhone("123456");
        service.set("user", user);
        UserInfo cacheUser = service.get("user", UserInfo.class);
        System.out.println(cacheUser);

//        LuaScriptService service = ctx.getBean(LuaScriptService.class);

//        List<String> keys1 = new ArrayList<>();
//        keys1.add("number");
//        Long result = service.execute("incrbyAndExpire.lua", Long.class, keys1, "1", "3000");
//        System.out.println(result);

//        List<String> keys2 = new ArrayList<>();
//        keys2.add("list");
//        Boolean result2 = service.execute("lpushAndLimit.lua", Boolean.class, keys2, "qqq", "3");
//        System.out.println(result2);

//        List<String> keys3 = new ArrayList<>();
//        keys3.add("limitNum");
//        List<?> result3 = service.execute("incrbyAndLimit.lua", List.class, keys3, "3", "10");
//        System.out.println(result3);
    }
}
