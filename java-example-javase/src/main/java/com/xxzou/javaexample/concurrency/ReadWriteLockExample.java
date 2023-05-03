package com.xxzou.javaexample.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    public static void main(String[] args) {
        Cache<String, String> cache = new Cache<>();
        cache.set("key1", "val1");
        String val1 = cache.get("key1");
        System.out.println(val1);
    }

    static class Cache<K, V>{

        private Map<K, V> map = new HashMap<>();
        private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private Lock readLock = readWriteLock.readLock();
        private Lock writeLock = readWriteLock.writeLock();

        public V get(K key){
            try{
                readLock.lock();
                return map.get(key);
            }finally {
                readLock.unlock();
            }
        }

        public V computeIfAbsent(K key){
            V val = get(key);
            if(val != null){
                return val;
            }

            try{
                writeLock.lock();
                //select from db。。
                val = get(key);
                if(val == null){
//                    val = getValFromDb(key);
                    map.put(key, val);
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                writeLock.unlock();
            }
            return val;
        }

        public void set(K key, V val){
            try{
                writeLock.lock();
                map.put(key, val);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                writeLock.unlock();
            }
        }


    }

}
