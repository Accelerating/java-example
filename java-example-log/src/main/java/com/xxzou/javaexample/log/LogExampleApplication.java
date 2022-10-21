package com.xxzou.javaexample.log;

import com.xxzou.javaexample.log.fixedwindow.FixedWindowLogService;
import com.xxzou.javaexample.log.timebase.TimebaseLogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
/**
 * @author zxx
 * @date 2022/10/18 11:18
 */
@SpringBootApplication
public class LogExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(LogExampleApplication.class, args);
        LogService service = ctx.getBean(LogService.class);

        service.log("root log");


        TimebaseLogService timebaseLogService = ctx.getBean(TimebaseLogService.class);
        timebaseLogService.log("time base log");

        FixedWindowLogService fixedWindowLogService = ctx.getBean(FixedWindowLogService.class);
        for (int i = 0; i < 5000; i++) {
            fixedWindowLogService.log("fixed window log");
        }

    }
}
