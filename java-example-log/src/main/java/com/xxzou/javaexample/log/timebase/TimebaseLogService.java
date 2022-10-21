package com.xxzou.javaexample.log.timebase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zxx
 * @date 2022/10/21 18:14
 */
@Service
public class TimebaseLogService {

    private static final Logger log =  LoggerFactory.getLogger(TimebaseLogService.class);

    public void log(String msg){
        log.debug(msg);
        log.info(msg);
        log.warn(msg);
        log.error(msg);
    }

}
