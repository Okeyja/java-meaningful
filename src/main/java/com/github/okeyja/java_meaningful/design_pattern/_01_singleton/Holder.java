package com.github.okeyja.java_meaningful.design_pattern._01_singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 静态内部类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Holder {
    public Holder getInstance() {
        return InnerClass.HOLDER;
    }

    public static class InnerClass {
        private static final Holder HOLDER = new Holder();
    }
}
