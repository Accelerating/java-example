package com.xxzou.javaexample.log.jul;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaUtilLoggingExample {

    private static final Logger log = Logger.getLogger(JavaUtilLoggingExample.class.getName());


    public static void main(String[] args) {

        /*
            log level:
                        SEVERE
                        WARNING
                        INFO (default level)
                        CONFIG
                        FINE
                        FINER
                        FINEST
         */
        log.severe("server log");
        log.warning("warning log");
        log.info("info log");
        log.config("config log");
        log.fine("fine log");
        log.finer("finer log");
        log.finest("finest log");
    }


}
