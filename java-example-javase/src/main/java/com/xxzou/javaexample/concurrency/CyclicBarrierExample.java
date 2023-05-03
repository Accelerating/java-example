package com.xxzou.javaexample.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CyclicBarrier 用于一组线程之间互相等待
 * 调用await方法之后会对计数器减一。如果减到零之后会重置计数器为初始值，并且如果有传入回调方法的话，会调用回调方法
 *
 */
public class CyclicBarrierExample {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        //可以传入一个回调方法，也可以自己定义一个线程池去执行回调方法，如果不定义其他线程去执行，则由最后一个完成的线程去执行这个回调方法
        CyclicBarrier barrier = new CyclicBarrier(3, ()->{
            System.out.println(Thread.currentThread().getName() + "重置计数器");
        });

        for (int i = 0; i < 10; i++) {

            Task t1 = new Task(barrier, ThreadLocalRandom.current().nextInt(1,3) * 1000);
            Task t2 = new Task(barrier, ThreadLocalRandom.current().nextInt(1,3) * 1000);
            t1.start();
            t2.start();
            barrier.await();
        }

    }

    static class Task extends Thread{

        private final CyclicBarrier barrier;
        private final long executeTime;

        public Task(CyclicBarrier barrier, long executeTime){
            this.barrier = barrier;
            this.executeTime = executeTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(executeTime);
                System.out.println(Thread.currentThread().getName() + "执行完成");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
