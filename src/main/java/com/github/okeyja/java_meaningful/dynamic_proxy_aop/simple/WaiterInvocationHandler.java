package com.github.okeyja.java_meaningful.dynamic_proxy_aop.simple;

import com.github.okeyja.java_meaningful.dynamic_proxy_aop.Waiter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WaiterInvocationHandler implements InvocationHandler {

    private final Waiter waiter;

    public WaiterInvocationHandler(Waiter waiter) {
        this.waiter = waiter;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("你好");
        waiter.server();//调用目标对象的方法
        System.out.println("再见");
        return null;
    }
}