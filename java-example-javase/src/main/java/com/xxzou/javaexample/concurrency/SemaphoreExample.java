package com.xxzou.javaexample.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    //信号量允许指定数量个的线程同时访问指定的资源，超过的数量的线程需要等待信号量被释放，根据这个可以实现一个限流的功能
    //一个典型的应用就是各种连接池或者对象池
    public static void main(String[] args) throws InterruptedException {


        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 200; i++) {
            new Thread(()->{
                try{
                    semaphore.acquire();
                    //业务逻辑，只允许规定数量的线程进入到这部分里面去执行，如果信号量的值设置为1，那就跟普通的锁差不多了

                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }








    }


}
