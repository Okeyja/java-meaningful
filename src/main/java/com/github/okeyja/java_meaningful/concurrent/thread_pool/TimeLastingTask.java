package com.github.okeyja.java_meaningful.concurrent.thread_pool;

import java.util.concurrent.ThreadPoolExecutor;

public class TimeLastingTask implements Runnable {

    private final int taskId;
    private final ThreadPoolExecutor executor;

    public TimeLastingTask(int taskId, ThreadPoolExecutor executor) {
        this.taskId = taskId;
        this.executor = executor;
    }

    @Override
    public void run() {
        try {
            System.out.println("TASK " + taskId + " DOING, thread: " + Thread.currentThread().getName());
            Thread.sleep(3000);
            System.out.println("TASK " + taskId + " DONE, thread: " + Thread.currentThread().getName());
            System.out.println(
                    "线程池中线程数目：" + executor.getPoolSize() +
                            "，队列中等待执行的任务数目：" + executor.getQueue().size() +
                            "，已执行玩别的任务数目：" + executor.getCompletedTaskCount()
            );
        } catch (InterruptedException e) {
            System.err.println("TASK " + taskId + " FAILED, thread: " + Thread.currentThread().getName());
            e.printStackTrace();
        }
    }
}
