package com.xxzou.javaexample.redis.spring.pubsub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author zxx
 * @date 2022/10/10 11:51
 */
@Component
public class RedisSubscriber implements MessageListener {
    /**
     * Callback for processing received objects through Redis.
     *
     * @param message message must not be {@literal null}.
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {

        String channel = new String(pattern);
        String msg = new String(message.getBody());
        System.out.println(channel + ":" + msg);
    }
}
