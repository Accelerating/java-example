package com.xxzou.javaexample.net.netty.tcp.rpc.service;

import com.xxzou.javaexample.net.netty.tcp.rpc.entity.NettyRpcUserInfo;

public interface NettyRpcUserService {

    NettyRpcUserInfo findUserById(Long id);

    void saveUserInfo(NettyRpcUserInfo userInfo);

}
