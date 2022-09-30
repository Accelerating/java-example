package com.xxzou.javaexample.javase.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zxx
 * @date 2022/9/30 11:09
 */
public class DateExample {

    public static void main(String[] args) {

    }

    public static void getTimestamp(){

        // from System
        long timestamp1 = System.currentTimeMillis();

        // from java.util.Date
        long timestamp2 = new Date().getTime();

        // from java.time.Instant
        long timestamp3 = Instant.now().toEpochMilli();

        // ZonedDateTime -> Instant -> Timestamp
        long timestamp4 = ZonedDateTime.now(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // LocalDateTime -> Instant -> Timestamp
        long timestamp5 = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        System.out.println(timestamp1);
        System.out.println(timestamp2);
        System.out.println(timestamp3);
        System.out.println(timestamp4);
        System.out.println(timestamp5);
    }

    public static void getDate(){
        // from constructor
        Date d1 = new Date();

        // from Calendar
        Date d2 = Calendar.getInstance().getTime();

        // from LocalDateTime
        // LocalDateTime -> Instant -> Date
        Date d3 = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

        // from ZonedDateTime
        // ZonedDateTime -> Instant -> Date
        Date d4 = Date.from(ZonedDateTime.now().toInstant());
    }

    public static void getLocalDateTime(){

        // now()
        LocalDateTime ldt1 = LocalDateTime.now();

        // Date -> LocalDateTime
        LocalDateTime ldt2 = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

        // timestamp -> LocalDateTime
        LocalDateTime ldt3 = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());

    }


}
