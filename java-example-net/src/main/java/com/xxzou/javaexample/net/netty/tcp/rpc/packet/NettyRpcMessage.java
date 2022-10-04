package com.xxzou.javaexample.net.netty.tcp.rpc.packet;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NettyRpcMessage {



    private Long msgId;

    /**
     * className.methodName[argTypes...]
     */
    private String service;

    private Object[] args;

    private byte[] result;




    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }

    @JsonIgnore
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
