package com.github.okeyja.java_meaningful.dynamic_proxy_aop;

public class ManWaiter implements Waiter {
    @Override
    public void server() {
        System.out.println("正在服务……");
    }
}
