package com.github.okeyja.java_meaningful.concurrent.thread_pool.scheduled_executor;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorSample {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
        executor.scheduleAtFixedRate(() -> System.out.println("执行任务！" + new Date()), 0, 10, TimeUnit.SECONDS);
    }
}
