package com.xxzou.javaexample.net.netty.tcp.rpc.entity;

import java.util.Date;

public class NettyRpcUserInfo {

    private Long id;
    private String name;
    private Date createTime;

    public NettyRpcUserInfo() {
    }

    public NettyRpcUserInfo(Long id, String name, Date createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "NettyRpcUserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

