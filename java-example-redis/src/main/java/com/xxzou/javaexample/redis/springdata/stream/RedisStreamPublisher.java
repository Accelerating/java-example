package com.xxzou.javaexample.redis.springdata.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @author zxx
 * @date 2022/10/10 14:12
 */
@Component
public class RedisStreamPublisher {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public void publish(String topic, String message){
//        Map<String, String> content = Collections.singletonMap("content", message);
//        redisTemplate.opsForStream().add(topic, content);

        StringRecord record = StringRecord.of(Collections.singletonMap("content", message)).withStreamKey(topic);
        RecordId recordId = redisTemplate.opsForStream().add(record);
        System.out.println(recordId);
    }

}
