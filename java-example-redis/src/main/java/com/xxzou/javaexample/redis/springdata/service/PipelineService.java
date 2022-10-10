package com.xxzou.javaexample.redis.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/10 10:51
 */
@Service
public class PipelineService {

    private StringRedisTemplate redisTemplate;

    public PipelineService(@Autowired StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void testPipeline(SessionCallback<Object> sessionCallback){
        List<Object> results = redisTemplate.executePipelined(sessionCallback);
        System.out.println(results);
    }

}
