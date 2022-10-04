package com.xxzou.javaexample.net.netty.tcp.rpc.server.impl;

import com.xxzou.javaexample.net.netty.tcp.rpc.service.NettyRpcIntCalculateService;

public class NettyRpcIntCalculateServiceImpl implements NettyRpcIntCalculateService {
    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public int add(int x, int y, int z) {
        return x + y + z;
    }

    @Override
    public int minus(int x, int y) {
        return x - y;
    }

    @Override
    public int multiply(int x, int y) {
        return x * y;
    }

    @Override
    public int divide(int x, int y) {
        return x / y;
    }
}
