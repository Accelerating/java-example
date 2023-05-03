package com.xxzou.javaexample.rabbitmq.spring;

import com.xxzou.javaexample.rabbitmq.entity.RabbitMessage;
import com.xxzou.javaexample.utils.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

public class JsonMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(JsonUtils.serialize(o), messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return JsonUtils.toObject(message.getBody(), RabbitMessage.class);
    }
}
