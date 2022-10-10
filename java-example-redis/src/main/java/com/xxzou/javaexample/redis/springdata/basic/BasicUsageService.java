package com.xxzou.javaexample.redis.springdata.basic;

import com.xxzou.javaexample.redis.springdata.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BasicUsageService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String get(String key){
        return get(key, null);
    }

    public <T> T get(String key, Class<T> toValueType){
        String data = redisTemplate.opsForValue().get(key);
        if(toValueType == null){
            return (T)data;
        }
        return JsonUtils.toObject(data, toValueType);
    }

    public void set(String key, Object value){
        String json = JsonUtils.toJson(value);
        redisTemplate.opsForValue().set(key, json);
    }

    public void hset(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }
}

