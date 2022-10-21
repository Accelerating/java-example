package com.xxzou.javaexample.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zxx
 * @date 2022/10/18 11:19
 */
@Service
public class LogService {

    private Logger log =  LoggerFactory.getLogger(LogService.class);

    public void log(String msg){
        log.trace(msg);
        log.debug(msg);
        log.info(msg);
        log.warn(msg);
        log.error(msg);
    }

}
