package com.xxzou.javaexample.redis.springdata.pubsub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author zxx
 * @date 2022/10/10 12:09
 */
@Configuration
public class PubSubConfig {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory, RedisSubscriber subscriber){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(subscriber, new ChannelTopic("channel1"));
        return container;

    }

}
