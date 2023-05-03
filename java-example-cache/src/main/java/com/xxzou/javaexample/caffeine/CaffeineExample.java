package com.xxzou.javaexample.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineExample {

    public static void main(String[] args) throws InterruptedException {

        Cache<String, Object> cache = Caffeine.newBuilder().expireAfterAccess(500, TimeUnit.MICROSECONDS).build();
        Object time = cache.get("time", (k) -> System.currentTimeMillis());
        System.out.println(time);
        Thread.sleep(1000);
        time = cache.get("time", (k) -> System.currentTimeMillis());
        System.out.println(time);
    }
}
