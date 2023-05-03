package com.xxzou.block;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class BlockUtils {

    private static final Logger log = LoggerFactory.getLogger(BlockUtils.class);

    public static void sleep(long mills){
        try{
            Thread.sleep(mills);
        }catch (InterruptedException ie){
            log.error("", ie);
        }
    }

    public static void sleep(TimeUnit timeUnit, long time){
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException ie) {
            log.error("", ie);
        }
    }

    public static String waitForInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }


}
