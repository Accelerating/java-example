package com.xxzou.javaexample.net.netty.tcp.rpc.server.impl;

import com.xxzou.javaexample.net.netty.tcp.rpc.entity.NettyRpcUserInfo;
import com.xxzou.javaexample.net.netty.tcp.rpc.service.NettyRpcUserService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyRpcUserServiceImpl implements NettyRpcUserService {

    private Map<Long, NettyRpcUserInfo> userMap = new ConcurrentHashMap<>();

    public NettyRpcUserServiceImpl(){
        long now = System.currentTimeMillis();
        for (long i = 1; i <= 10; i++) {
            userMap.put(i, new NettyRpcUserInfo(i, "user"+i, new Date(now - i*86400000)));
        }

    }

    @Override
    public NettyRpcUserInfo findUserById(Long id) {
        return userMap.get(id);
    }

    @Override
    public void saveUserInfo(NettyRpcUserInfo userInfo) {
        userMap.put(userInfo.getId(), userInfo);
    }
}
