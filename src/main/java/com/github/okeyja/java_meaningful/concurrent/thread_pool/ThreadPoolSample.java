package com.github.okeyja.java_meaningful.concurrent.thread_pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://www.cnblogs.com/dolphin0520/p/3932921.html
 */
public class ThreadPoolSample {
    public static void main(String[] args) {
        /*
         * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
         * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(16, 32, 8, TimeUnit.HOURS, new LinkedBlockingQueue<>(998));
        for (int i = 0; i < 64; i++) {
            TimeLastingTask myTask = new TimeLastingTask(i, executor);
            executor.execute(myTask);
            System.out.println(
                    "线程池中线程数目：" + executor.getPoolSize() +
                            "，队列中等待执行的任务数目：" + executor.getQueue().size() +
                            "，已执行玩别的任务数目：" + executor.getCompletedTaskCount()
            );
        }
        executor.shutdown();
    }
}
