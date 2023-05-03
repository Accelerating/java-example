package com.xxzou.javaexample.concurrency;

import java.util.concurrent.locks.StampedLock;

/**
 * 在jdk1.8之后，提供了提供了一种叫 StampedLock 的锁，它的性能就比读写锁还要好。
 * ReadWriteLock 支持两种模式：一种是读锁，一种是写锁。
 * 而 StampedLock 支持三种模式，分别是：写锁、悲观读锁和乐观读。注意乐观读实际上是无锁的
 */
public class StampedLockExample {

    public static void main(String[] args) {
        /**
         * StampedLock 的性能之所以比 ReadWriteLock 还要好，其关键是 StampedLock 支持乐观读的方式。
         * ReadWriteLock 支持多个线程同时读，但是当多个线程同时读的时候，所有的写操作会被阻塞；
         * 而 StampedLock 提供的乐观读，是允许一个线程获取写锁的，也就是说不是所有的写操作都被阻塞。
         */

        /**
         * 其实乐观读的思想和数据库里面的乐观锁是类似的，与ReadWriteLock相比，StampedLock增加了一个tryOptimisticRead方法，这个方法会返回一个stamp，这个值用于进行乐观锁
         */

        /**
         *如果线程阻塞在 StampedLock 的 readLock() 或者 writeLock() 上时，此时调用该阻塞线程的 interrupt() 方法，会导致 CPU 飙升。
         * 例如线程 T1 获取写锁之后将自己阻塞，线程 T2 尝试获取悲观读锁，也会阻塞；
         * 如果此时调用线程 T2 的 interrupt() 方法来中断线程 T2 的话，你会发现线程 T2 所在 CPU 会飙升到 100%。
         *
         * 所以，使用 StampedLock 一定不要调用中断操作，如果需要支持中断功能，一定使用可中断的悲观读锁 readLockInterruptibly() 和写锁 writeLockInterruptibly()。
         */


    }

    static class Account{
        private int balance;
        private final StampedLock stampedLock = new StampedLock();

        public void addBalance(int amount){
            long stamp = stampedLock.writeLock();
            try{
                this.balance = this.balance + amount;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                stampedLock.unlockWrite(stamp);
            }

        }

        public int getBalance(){
            long stamp = stampedLock.tryOptimisticRead();
            int amount = this.balance;
            if(!stampedLock.validate(stamp)){
                //如果乐观读不成功，直接转成悲观读锁，不用循环空转来增加cpu压力
                stamp = stampedLock.readLock();
                try{
                    amount = balance;
                }finally {
                    stampedLock.unlockRead(stamp);
                }
            }
            return amount;
        }


    }





}
