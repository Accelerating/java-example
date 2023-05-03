package com.xxzou.javaexample.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join 是一个并行计算的框架，主要就是用来支持分治任务模型的，这个计算框架里的 Fork 对应的是分治任务模型里的任务分解，Join 对应的是结果合并。
 * Fork/Join 计算框架主要包含两部分，一部分是分治任务的线程池 ForkJoinPool，另一部分是分治任务 ForkJoinTask。
 * 这两部分的关系类似于 ThreadPoolExecutor 和 Runnable 的关系，都可以理解为提交任务到线程池，只不过分治任务有自己独特类型 ForkJoinTask。
 */
public class ForkJoinExample {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Integer result = pool.invoke(new SumTask(1, 10000));
        System.out.println(result);
    }


    /**
     * ForkJoinTask 是一个抽象类，它的方法有很多，最核心的是 fork() 方法和 join() 方法，
     * 其中 fork() 方法会异步地执行一个子任务，而 join() 方法则会阻塞当前线程来等待子任务的执行结果。
     * ForkJoinTask 有两个子类——RecursiveAction 和 RecursiveTask，通过名字你就应该能知道，
     * 它们都是用递归的方式来处理分治任务的。这两个子类都定义了抽象方法 compute()，
     * 不过区别是 RecursiveAction 定义的 compute() 没有返回值，而 RecursiveTask 定义的 compute() 方法是有返回值的。
     * 这两个子类也是抽象类，在使用的时候，需要你定义子类去扩展。
     */
    static class SumTask extends RecursiveTask<Integer> {

        private int start;
        private int end;

        public SumTask(int start, int end){
            this.start = start;
            this.end = end;
        }


        @Override
        protected Integer compute() {
            int diff = end - start;
            if(diff < 100){
                int sum = 0;
                for (int i=start; i<=end; i++){
                    sum = sum + i;
                }
                return sum;
            }else{
                int half = diff / 2;
                SumTask subTask1 = new SumTask(start, start + half);
                SumTask subTask2 = new SumTask(start+half+1, end);
                subTask1.fork();
                subTask2.fork();

                Integer result1 = subTask1.join();
                Integer result2 = subTask2.join();

                return result1 + result2;
            }
        }
    }
}
