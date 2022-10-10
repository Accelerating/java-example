package com.xxzou.javaexample.redis.springdata.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zxx
 * @date 2022/10/10 14:14
 */
@Component
public class RedisStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void init(){
        try{
            String group = redisTemplate.opsForStream().createGroup(RedisStreamConfig.TEST_TOPIC, RedisStreamConfig.TEST_GROUP);
            System.out.println("create stream consume group -->" + group);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        System.out.println("receive message --> " + message);
    }
}
