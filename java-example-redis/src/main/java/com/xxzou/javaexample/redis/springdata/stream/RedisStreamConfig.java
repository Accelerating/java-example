package com.xxzou.javaexample.redis.springdata.stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zxx
 * @date 2022/10/10 14:58
 */
@Configuration
public class RedisStreamConfig{

    public static final String TEST_TOPIC = "test-topic";
    public static final String TEST_GROUP = "test-group";
    public static final String TEST_CONSUMER = "test-consumer";

    @Bean
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer(RedisConnectionFactory factory, RedisStreamListener listener){
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> container = StreamMessageListenerContainer.create(factory, options());
        container.receive(Consumer.from(TEST_GROUP, TEST_CONSUMER), StreamOffset.create(TEST_TOPIC, ReadOffset.lastConsumed()), listener);
        container.start();
        return container;
    }

    private StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options(){
        return StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                .batchSize(20)
                .pollTimeout(Duration.ofMillis(500))
                .serializer(new StringRedisSerializer())
//                .executor(customThreadPoolExecutor)
                .build();

    }

}
