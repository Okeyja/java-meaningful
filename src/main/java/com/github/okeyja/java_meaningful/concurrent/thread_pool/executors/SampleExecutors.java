package com.github.okeyja.java_meaningful.concurrent.thread_pool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SampleExecutors {

    /**
     * 通过实现Runnable接口，实现多线程
     * Runnable类是有run()方法的；
     * 但是没有start方法
     * 参考：    http://blog.csdn.net/qq_31753145/article/details/50899119
     **/
    public static class MyThread extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            // super.run();
            System.out.println(Thread.currentThread().getName() + "正在执行....");
        }
    }

    public static void single() {
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口;
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();

        //将线程放到池中执行；
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        //关闭线程池
        pool.shutdown();
    }

    public static void fixed() {
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口;
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();

        //将线程放到池中执行；
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        //关闭线程池
        pool.shutdown();
    }

    public static void cached() {
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newCachedThreadPool();

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口;
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();

        //将线程放到池中执行；
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        //关闭线程池
        pool.shutdown();
    }

    public static void scheduled() {
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        //每隔一段时间就触发异常
        exec.scheduleAtFixedRate(() -> System.out.println("==============="), 1000, 5000, TimeUnit.MILLISECONDS);

        //每隔一段时间打印系统时间，证明两者是互不影响的
        exec.scheduleAtFixedRate(() -> System.out.println(System.nanoTime()), 1000, 2000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        // single();
        // fixed();
        // cached();
         scheduled();
    }
}
