package com.xxzou.javaexample.rabbitmq.entity;

import com.xxzou.javaexample.utils.JsonUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RabbitMessage {

    private Long id;
    private String name;
    private Integer num;
    private List<String> strArray;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<String> getStrArray() {
        return strArray;
    }

    public void setStrArray(List<String> strArray) {
        this.strArray = strArray;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    public byte[] serialize(){
        return JsonUtils.serialize(this);
    }

    public static RabbitMessage randomMessage(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        RabbitMessage msg = new RabbitMessage();
        msg.setId(System.currentTimeMillis());
        msg.setName("消息:" + random.nextInt(100, 200));
        msg.setNum(random.nextInt(100000, 999999));
        msg.setStrArray(Stream.of(UUID.randomUUID().toString(), UUID.randomUUID().toString()).collect(Collectors.toList()));
        return msg;
    }
}
