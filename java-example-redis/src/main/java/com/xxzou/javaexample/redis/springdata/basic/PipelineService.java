package com.xxzou.javaexample.redis.springdata.basic;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void testPipeline(SessionCallback<Object> sessionCallback){
        List<Object> results = redisTemplate.executePipelined(sessionCallback);
        System.out.println(results);
    }

}
