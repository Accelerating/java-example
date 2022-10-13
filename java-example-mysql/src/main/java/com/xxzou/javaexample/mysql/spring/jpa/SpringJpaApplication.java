package com.xxzou.javaexample.mysql.spring.jpa;

import com.xxzou.javaexample.mysql.entity.UserInfo;
import com.xxzou.javaexample.mysql.spring.jpa.repository.UserJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/13 12:07
 */

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.xxzou.javaexample.mysql.spring.jpa.repository")
@EntityScan(basePackages = "com.xxzou.javaexample.mysql.entity")
public class SpringJpaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJpaApplication.class, args);
        UserJpaRepository userJpaRepository = ctx.getBean(UserJpaRepository.class);
        List<UserInfo> userList = userJpaRepository.findByRoleId(2L);
        System.out.println(userList);
    }
}
