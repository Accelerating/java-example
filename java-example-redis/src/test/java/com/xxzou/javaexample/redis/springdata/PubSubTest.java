package com.xxzou.javaexample.redis.springdata;

import com.xxzou.javaexample.redis.springdata.pubsub.RedisPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zxx
 * @date 2022/10/10 11:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PubSubTest {

    @Autowired
    private RedisPublisher publisher;

    @Test
    public void testPublish(){
        publisher.publish("channel1", "hello world");
    }

}
