package com.xxzou.javaexample.mysql.spring.mybatis;

import com.xxzou.javaexample.mysql.entity.UserInfo;
import com.xxzou.javaexample.mysql.spring.mybatis.mapper.UserInfoMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/13 12:08
 */
@MapperScan(basePackages = "com.xxzou.javaexample.mysql.spring.mybatis.mapper")
@SpringBootApplication
public class MybatisSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MybatisSpringApplication.class, args);
        UserInfoMapper mapper = ctx.getBean(UserInfoMapper.class);
        List<UserInfo> userList = mapper.findUserByName("jack");
        System.out.println(userList);
    }
}
