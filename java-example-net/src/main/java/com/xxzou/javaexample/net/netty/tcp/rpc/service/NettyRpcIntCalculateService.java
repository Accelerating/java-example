package com.xxzou.javaexample.net.netty.tcp.rpc.service;

public interface NettyRpcIntCalculateService {

    int add(int x, int y);
    int add(int x, int y, int z);
    int minus(int x, int y);
    int multiply(int x, int y);
    int divide(int x, int y);

}
