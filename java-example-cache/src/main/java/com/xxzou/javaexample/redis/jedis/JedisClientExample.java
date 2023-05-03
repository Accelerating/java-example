package com.xxzou.javaexample.redis.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientExample {

    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new GenericObjectPoolConfig(), "127.0.0.1", 6379, 1000, "1234567890");
        Jedis jedis = pool.getResource();
//        jedis.set(key);
//        jedis.get(key);
//        jedis.hset(key, field, value);
//        jedis.hget(key, field);
//        ......
    }

}
