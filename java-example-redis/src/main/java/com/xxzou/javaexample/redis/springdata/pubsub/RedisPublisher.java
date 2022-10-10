package com.xxzou.javaexample.redis.springdata.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zxx
 * @date 2022/10/10 11:51
 */
@Component
public class RedisPublisher {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void publish(String channel, String msg){
        redisTemplate.convertAndSend(channel, msg);
    }
}
