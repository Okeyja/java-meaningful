package com.github.okeyja.java_meaningful.concurrent.lifecycle;

/**
 * Thread sleep(): 线程保持锁的同时 Blocked。
 * Object wait(): 这个 Object 作为锁的时候，释放锁 Blocked。
 * Object notify(): 这个 Object 作为锁的时候，随机调取一个 Blocked 的线程进入 Runnable。
 * Object notifyAll(): 这个 Object 作为锁的时候，调取所有 Blocked 的线程进入 Runnable。
 */
public class SleepAndWait {

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            synchronized (SleepAndWait.class) {
                System.out.println("Enter thread-1.");
                System.out.println("Thread-1 is waiting...");
                try {
                    // 调用 wait() 方法，线程会放弃对象锁，进入等待此对象的等待锁定池。
                    SleepAndWait.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Thread-1 is going on ....");
                System.out.println("Thread-1 is over!!!");
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            synchronized (SleepAndWait.class) {
                System.out.println("Enter thread-2.");
                System.out.println("Thread-2 is sleeping....");
                /*
                 * 只有针对此对象调用 notify() 方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
                 * 如果我们把代码：`SleepAndWait.class.notify();` 给注释掉，即 TestD.class 调用了 wait() 方法，
                 * 但是没有调用 notify() 方法，则线程永远处于挂起状态。
                 */
                SleepAndWait.class.notify();
                try {
                    /*
                     * sleep() 方法导致了程序暂停执行指定的时间，让出 cpu 该其他线程，
                     * 但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
                     * 在调用 sleep() 方法的过程中，线程不会释放对象锁。
                     */
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Thread-2 is going on....");
                System.out.println("Thread-2 is over!!!");
            }
        }
    }
}
