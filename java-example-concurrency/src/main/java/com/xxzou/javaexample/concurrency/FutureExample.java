package com.xxzou.javaexample.concurrency;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int time = ThreadLocalRandom.current().nextInt(1, 3);
                TimeUnit.SECONDS.sleep(time);
                return time;
            }
        });

        new Thread(task).start();
        Integer sleepTime = task.get();
        System.out.println(sleepTime);
    }

}
