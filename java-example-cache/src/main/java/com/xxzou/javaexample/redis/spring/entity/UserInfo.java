package com.xxzou.javaexample.redis.spring.entity;



import com.xxzou.serialiaze.json.JsonUtils;

import java.util.Date;

public class UserInfo {

    private Long id;
    private String name;
    private String phone;
    private Date createTime;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
