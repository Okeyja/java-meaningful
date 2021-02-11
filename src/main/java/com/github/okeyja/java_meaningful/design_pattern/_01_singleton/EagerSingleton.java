package com.github.okeyja.java_meaningful.design_pattern._01_singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 饿汉式单例
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EagerSingleton {

    /**
     * 创建即加载，浪费空间。
     */
    private byte[] data1 = new byte[1024 * 1024];
    private byte[] data2 = new byte[1024 * 1024];
    private byte[] data3 = new byte[1024 * 1024];
    private byte[] data4 = new byte[1024 * 1024];

    private final static EagerSingleton EAGER = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return EAGER;
    }
}
