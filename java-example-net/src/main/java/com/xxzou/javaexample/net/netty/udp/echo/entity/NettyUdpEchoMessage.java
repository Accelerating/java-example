package com.xxzou.javaexample.net.netty.udp.echo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.net.InetSocketAddress;

public class NettyUdpEchoMessage {

    private long msgId;
    private String content;

    @JsonIgnore
    private InetSocketAddress dest;
    @JsonIgnore
    private InetSocketAddress src;

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public InetSocketAddress getDest() {
        return dest;
    }

    public void setDest(InetSocketAddress dest) {
        this.dest = dest;
    }

    public InetSocketAddress getSrc() {
        return src;
    }

    public void setSrc(InetSocketAddress src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "[" + src + "] ---> [" + dest + "]: " + content;
    }
}
