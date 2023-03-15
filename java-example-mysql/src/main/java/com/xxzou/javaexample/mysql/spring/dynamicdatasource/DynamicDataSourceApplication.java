package com.xxzou.javaexample.mysql.spring.dynamicdatasource;

import com.xxzou.javaexample.mysql.spring.dynamicdatasource.config.DynamicDataSourceAspect;
import com.xxzou.javaexample.mysql.spring.dynamicdatasource.mapper.DataSourceAMapper;
import com.xxzou.javaexample.mysql.spring.dynamicdatasource.mapper.DataSourceBMapper;
import com.xxzou.javaexample.mysql.spring.dynamicdatasource.service.DataSourceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class DynamicDataSourceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DynamicDataSourceApplication.class, args);
        DataSourceAMapper aMapper = ctx.getBean(DataSourceAMapper.class);
        DataSourceBMapper bMapper = ctx.getBean(DataSourceBMapper.class);


        System.out.println(aMapper.getDataSourceName());
        System.out.println(bMapper.getDataSourceName());

//        DataSourceService service = ctx.getBean(DataSourceService.class);
//        System.out.println(service.getDataSource());
    }

}
