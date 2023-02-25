package com.xxzou.javaexample.concurrency;


import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class AwaitSignalExample {

    public static void main(String[] args) {
        Task t1 = new Task(1L);
        Task t2 = new Task(2L);
        Task t3 = new Task(3L);

        TaskExecutor executor = new TaskExecutor();
        executor.start();
        executor.submit(t1);
        executor.submit(t2);
        executor.submit(t3);
        System.out.println(t3.getResult());
    }


    static class TaskExecutor{
        private LinkedBlockingQueue<Task> tasks = new LinkedBlockingQueue<>();

        public void start(){
            new Thread(()->{
                while(true){

                    try {
                        Task task = tasks.take();
                        TimeUnit.SECONDS.sleep(2);
                        task.setResult("ok-" + task.getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

        public void submit(Task task){
            tasks.add(task);
        }

    }

    static class Task{
        private final Long id;
        private Object result;
        private boolean done;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public Task(Long id){
            this.id = id;
        }

        public Long getId() {
            return id;
        }


        public Object getResult() {
            lock.lock();
            try {
                while(!done){
                    condition.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

            return result;
        }

        public void setResult(Object result) {
            lock.lock();
            try{
                this.result = result;
                this.done = true;
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    '}';
        }
    }

}
