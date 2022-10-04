package com.xxzou.javaexample.net.netty.tcp.rpc.client;

import com.xxzou.javaexample.net.netty.tcp.rpc.client.proxy.NettyRpcClientProxy;
import com.xxzou.javaexample.net.netty.tcp.rpc.entity.NettyRpcUserInfo;
import com.xxzou.javaexample.net.netty.tcp.rpc.service.NettyRpcIntCalculateService;
import com.xxzou.javaexample.net.netty.tcp.rpc.service.NettyRpcUserService;

public class NettyRpcClientExample {

    public static void main(String[] args) {
        NettyRpcIntCalculateService calculateService = NettyRpcClientProxy.getService(NettyRpcIntCalculateService.class);
        System.out.println("add: " + calculateService.add(100, 200));
        System.out.println("add3: " + calculateService.add(100, 200, 300));
        System.out.println("minus: " + calculateService.minus(100, 200));
        System.out.println("multiply: " + calculateService.multiply(3, 7));
        System.out.println("divide: " + calculateService.divide(256, 2));

        NettyRpcUserService userService = NettyRpcClientProxy.getService(NettyRpcUserService.class);
        long userId = 2;
        NettyRpcUserInfo user = userService.findUserById(userId);
        System.out.println("findUserById: " + user);

        user.setName("jack");
        userService.saveUserInfo(user);

        NettyRpcUserInfo user2 = userService.findUserById(userId);
        System.out.println("findUserById: " + user2);

        NettyRpcClientProxy.close();
    }

}
