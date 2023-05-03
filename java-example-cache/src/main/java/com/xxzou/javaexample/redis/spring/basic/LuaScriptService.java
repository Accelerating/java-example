package com.xxzou.javaexample.redis.spring.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LuaScriptService {

    private Map<String, RedisScript<?>> scriptCache = new ConcurrentHashMap<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    public <T> T execute(String scriptFileName, Class<T> resultType, List<String> keys, String... args){
        RedisScript<?> script = scriptCache.computeIfAbsent(scriptFileName, (k)->{
            ClassPathResource classPathResource = new ClassPathResource("lua/" + scriptFileName);
            ResourceScriptSource scriptSource = new ResourceScriptSource(classPathResource);
            Resource scriptResource = scriptSource.getResource();
            return RedisScript.of(scriptResource, resultType);
        });
        return (T)redisTemplate.execute(script, keys, args);
    }


}
