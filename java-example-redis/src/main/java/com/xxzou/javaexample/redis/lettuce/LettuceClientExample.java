package com.xxzou.javaexample.redis.lettuce;

import io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;


public class LettuceClientExample {

    public static void main(String[] args) throws Exception {
        RedisURI redisURI = new RedisURI("127.0.0.1", 6379, Duration.ofSeconds(3));
        redisURI.setPassword(new StringBuilder("1234567890"));
        RedisClient redis = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = redis.connect();
        //sync api
//        RedisCommands<String, String> redisSyncCommand = connect.sync();
//        redisSyncCommand.set(key, value);
//        redisSyncCommand.get(key);
//        redisSyncCommand.hset(key, field, value);
//        redisSyncCommand.hget(key, field);
//        ......


        //async api
//        RedisAsyncCommands<String, String> redisAsyncCommand = connect.async();
//        RedisFuture<String> future = redisAsyncCommand.set("key", "value");
//        future.get();


        //reactive api
//        RedisReactiveCommands<String, String> redisReactiveCommand = connect.reactive();
//        Mono<String> mono = redisReactiveCommand.set("key", "value");
//        String value = mono.block();
//        Flux<KeyValue<String, String>> flux = redisReactiveCommand.hgetall("hkey");
//        List<KeyValue<String, String>> kvs = flux.collectList().block();


        //use lua script
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL lua = classLoader.getResource("lua/incrbyAndExpire.lua");
        Path scriptPath = Paths.get(lua.toURI());
        byte[] script = Files.readAllBytes(scriptPath);
        RedisCommands<String, String> syncCommand = connect.sync();

        Object execResult = syncCommand.eval(script, ScriptOutputType.INTEGER, new String[]{"number"}, "2", "1000");
        System.out.println(execResult);

        //use sha1 map to script
//        String sha1 = syncCommand.scriptLoad(script);
//        Object execResult = syncCommand.evalsha(sha1, ScriptOutputType.INTEGER, new String[]{"number"}, "2", "1000");
//        System.out.println(execResult);
    }



}
