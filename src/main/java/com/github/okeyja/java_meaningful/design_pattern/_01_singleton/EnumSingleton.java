package com.github.okeyja.java_meaningful.design_pattern._01_singleton;

import java.lang.reflect.Constructor;

/**
 * 枚举自带单例
 * <p>
 * 反射不会破坏单例
 */
public enum EnumSingleton {
    INSTANCE;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    /**
     * 反射试图破坏枚举1
     * <p>
     * 失败：找不到空参构造函数
     * java.lang.NoSuchMethodException: com.github.okeyja.java_meaningful.design_pattern.singleton.EnumSingleton.&lt;init&gt;()
     */
    public static void test1() throws Exception {
        EnumSingleton instance1 = EnumSingleton.getInstance();
        Constructor<EnumSingleton> enumSingletonConstructor = EnumSingleton.class.getDeclaredConstructor(); // 不能使用空参构造函数。枚举是语法糖，编译后将成为一个常规 class。
        enumSingletonConstructor.setAccessible(true);
        EnumSingleton instance2 = enumSingletonConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }

    /**
     * 反射试图破坏枚举2
     * <p>
     * 结果：反射抛出异常，不允许反射枚举
     * java.lang.IllegalArgumentException: Cannot reflectively create enum objects
     */
    public static void test2() throws Exception {
        EnumSingleton instance1 = EnumSingleton.getInstance();
        Constructor<EnumSingleton> enumSingletonConstructor = EnumSingleton.class.getDeclaredConstructor(String.class, int.class); // 不能使用空参构造函数。枚举是语法糖，编译后将成为一个常规 class。
        enumSingletonConstructor.setAccessible(true);
        EnumSingleton instance2 = enumSingletonConstructor.newInstance("AAA", 123);
        System.out.println(instance1);
        System.out.println(instance2);
    }

    public static void main(String[] args) throws Exception {
//        test1();
        test2();
    }
}
