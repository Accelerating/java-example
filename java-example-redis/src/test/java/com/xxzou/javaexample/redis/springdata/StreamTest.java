package com.xxzou.javaexample.redis.springdata;

import com.xxzou.javaexample.redis.springdata.stream.RedisStreamConfig;
import com.xxzou.javaexample.redis.springdata.stream.RedisStreamPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author zxx
 * @date 2022/10/10 14:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamTest {

    @Autowired
    private RedisStreamPublisher publisher;

    @Test
    public void testPublish(){
        publisher.publish(RedisStreamConfig.TEST_TOPIC, LocalDateTime.now().toString());
    }

}
