package com.github.okeyja.java_meaningful.design_pattern._01_singleton;

import lombok.Getter;

import java.lang.reflect.Constructor;
import java.util.UUID;

/**
 * 懒汉式单例模型：
 * <p>
 * 在有需要的时候创建对象，没有需要时不再创建。
 * 注意问题：一般多线程并发问题、指令重排并发问题。
 * 不能解决：反射破坏单例性。
 */
@Getter
public class LazySingleton {

    /**
     * volatile 避免了指令重排。
     * <p>
     * 理想情况下创建对象的顺序如下：
     * 1. 堆内开辟空间；
     * 2. 在空间内创建 Object 对象；
     * 3. 将空间引用地址赋值到变量。
     * <p>
     * 指令重排后可能出现的顺序如下：
     * 1. 堆内开辟空间；
     * 2. 将空间引用地址赋值到变量；
     * 3. 在空间内创建 Object 对象。
     * <p>
     * 上述操作不是原子性操作，指令重排后可能受线程切换过程打断，空间内实际的对象可能是 null。
     */
    private static volatile LazySingleton instance;
    private final String sampleDataField;

    /**
     * 私有构造函数，不允许创建对象。
     * <p>
     * 屏蔽通过第一类反射破坏单例封装。
     */
    private LazySingleton() {
        synchronized (LazySingleton.class) {
            if (instance != null) {
                throw new RuntimeException("不要试图通过反射破坏单例！");
            }
            sampleDataField = UUID.randomUUID().toString();
            System.out.printf("Creating Singleton Object. Thread Name: %s, Sample Data Field: %s.%n", Thread.currentThread().getName(), sampleDataField);
        }
    }

    public static LazySingleton getInstance() {
        if (instance == null) { // 外层检查，如果不是 volatile 指令重排后造成判断错误。
            synchronized (LazySingleton.class) { // 为创建对象的过程加锁，因为创建对象 & 赋值过程并不是原子性的。
                if (instance == null) { // 内层检查
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    /**
     * 并发安全测试
     */
    public static void test1() {
        for (int i = 0; i < 10; i++) {
            new Thread(LazySingleton::getInstance).start();
        }
    }

    /**
     * 第一类反射破坏测试
     * <p>
     * 构造函数中没有屏蔽时通过反射调用构造函数，单例被破坏。
     *
     * @throws Exception 所有异常
     */
    public static void test2() throws Exception {

        LazySingleton lazySingleton = LazySingleton.getInstance();

        Class<LazySingleton> lazyManClass = LazySingleton.class;
        Constructor<LazySingleton> lazyManConstructor = lazyManClass.getDeclaredConstructor();
        lazyManConstructor.setAccessible(true);
        LazySingleton lazySingletonHackedInstance2 = lazyManConstructor.newInstance();

        System.out.println(lazySingleton);
        System.out.println(lazySingletonHackedInstance2);

        if (lazySingleton != lazySingletonHackedInstance2) {
            System.err.println("单例被破坏！");
        }
    }

    /**
     * 第二类反射破坏测试
     * <p>
     * 构造函数中有屏蔽，但是所有单例都通过反射创建对象时依旧会被破坏
     *
     * @throws Exception 所有异常
     */
    public static void test3() throws Exception {
        Class<LazySingleton> lazyManClass = LazySingleton.class;
        Constructor<LazySingleton> lazyManConstructor = lazyManClass.getDeclaredConstructor();
        lazyManConstructor.setAccessible(true);
        LazySingleton lazySingletonHackedInstance1 = lazyManConstructor.newInstance();
        LazySingleton lazySingletonHackedInstance2 = lazyManConstructor.newInstance();

        System.out.println(lazySingletonHackedInstance1);
        System.out.println(lazySingletonHackedInstance2);

        if (lazySingletonHackedInstance1 != lazySingletonHackedInstance2) {
            System.err.println("单例被破坏！");
        }
    }

    public static void main(String[] args) throws Exception {
//        test1();
//        test2();
        test3();
    }
}
