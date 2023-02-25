package com.xxzou.javaexample.logback.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class ExampleController {

    private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

    @PostMapping("/test/info")
    public String infoLog(String msg){
        log.info(msg);
        return "info";
    }


    @PostMapping("/test/warn")
    public String warnLog(String msg){
        log.warn(msg);
        return "warn";
    }

    @PostMapping("/test/error")
    public String errorLog(String msg){

        ThreadLocalRandom random = ThreadLocalRandom.current();
        int ran = random.nextInt(1, 5);
        try{
            if(ran == 1){
                System.out.println(1 / 0);
            }else if(ran == 2){
                String s = null;
                s.split(",");
            }else if(ran == 3){
                List<Object> list = new ArrayList<>();
                list.get(ran);
            }else{
                throw new RuntimeException(msg);
            }

        }catch (Exception e){
            log.error("err msg:{}",msg, e);
        }

        return "error";
    }

}
