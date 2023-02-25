package com.xxzou.javaexample.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 主要用来解决一个线程等待多个线程的场景
 */
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(()->{
            try {
                Thread.sleep(2000L);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(3000L);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //await会阻塞调用的线程直至CountDownLatch计数变为0为止
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start) + "ms");

    }
}
