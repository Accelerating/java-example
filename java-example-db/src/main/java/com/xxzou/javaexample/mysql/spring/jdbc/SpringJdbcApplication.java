package com.xxzou.javaexample.mysql.spring.jdbc;

import com.xxzou.javaexample.mysql.entity.UserInfo;
import com.xxzou.javaexample.mysql.spring.jdbc.repository.UserJdbcRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/13 12:07
 */
@EntityScan(basePackages = "com.xxzou.javaexample.mysql")
@EnableJdbcRepositories(basePackages = "com.xxzou.javaexample.mysql.spring.jdbc.repository")
@EnableTransactionManagement
@SpringBootApplication
public class SpringJdbcApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJdbcApplication.class, args);
        UserJdbcRepository userRepository = ctx.getBean(UserJdbcRepository.class);
        List<UserInfo> userInfos = userRepository.findByAgeGreaterThanEqual(20);
        System.out.println(userInfos);
    }
}
