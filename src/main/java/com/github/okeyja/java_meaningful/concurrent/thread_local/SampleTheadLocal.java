package com.github.okeyja.java_meaningful.concurrent.thread_local;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

public class SampleTheadLocal {
    public static void main(String[] args) {
        DateFormat dfa = SafeDateFormat.get();
        System.out.printf("Thread name: %s,\t\tIdentity Hash code: %s\r\n", Thread.currentThread().getName(), System.identityHashCode(dfa));

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                DateFormat df = SafeDateFormat.get();
                System.out.printf("Thread name: %s,\tIdentity Hash code: %s\r\n", Thread.currentThread().getName(), System.identityHashCode(df));
            }).start();
        }
    }


    private static class SafeDateFormat {
        // 定义 ThreadLocal 变量
        private static final ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 定义共享的变量
        private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /**
         * 通过 ThreadLocal 实现的线程隔离型变量
         *
         * @return 非共享的 DateFormat
         */
        private static DateFormat get() {
            // 实际上的动作是 getOrDefault，线程中的 ThreadLocalMap 没有值时将填充值。
            return tl.get();
        }

        /**
         * 直接通过共享来获取非线程安全的变量
         *
         * @return 共享的 DateFormat
         */
        private static DateFormat getUnsafe() {
            return sdf;
        }
    }


    static class ThreadId {
        static final AtomicLong nextId = new AtomicLong(0);
        // 定义 ThreadLocal 变量
        static final ThreadLocal<Long> tl = ThreadLocal.withInitial(nextId::getAndIncrement);

        // 此方法会为每个线程分配一个唯一的Id
        static long get() {
            return tl.get();
        }
    }
}
