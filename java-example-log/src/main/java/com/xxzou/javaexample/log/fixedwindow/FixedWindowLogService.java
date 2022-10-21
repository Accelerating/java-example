package com.xxzou.javaexample.log.fixedwindow;

import com.xxzou.javaexample.log.timebase.TimebaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zxx
 * @date 2022/10/21 18:41
 */
@Service
public class FixedWindowLogService {

    private static final Logger log =  LoggerFactory.getLogger(FixedWindowLogService.class);


    public void log(String msg){
        log.debug(msg);
        log.info(msg);
        log.warn(msg);
        log.error(msg);
    }

}
