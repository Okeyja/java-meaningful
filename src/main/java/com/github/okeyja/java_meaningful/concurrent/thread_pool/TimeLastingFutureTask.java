package com.github.okeyja.java_meaningful.concurrent.thread_pool;

import java.util.concurrent.Callable;

public class TimeLastingFutureTask implements Callable<Integer> {
    private final int taskId;

    public TimeLastingFutureTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("FUTURE TASK " + taskId + " DOING, thread: " + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("FUTURE TASK " + taskId + " DONE, thread: " + Thread.currentThread().getName());
        return 888;
    }
}
