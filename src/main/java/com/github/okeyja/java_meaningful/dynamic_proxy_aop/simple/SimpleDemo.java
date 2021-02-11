package com.github.okeyja.java_meaningful.dynamic_proxy_aop.simple;

import com.github.okeyja.java_meaningful.dynamic_proxy_aop.ManWaiter;
import com.github.okeyja.java_meaningful.dynamic_proxy_aop.Waiter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class SimpleDemo {
    public static void main(String[] args) {
        Waiter manWaiter = new ManWaiter();
        ClassLoader classLoader = SimpleDemo.class.getClassLoader();
        Class<?>[] interfaces = {Waiter.class};
        InvocationHandler invocationHandler = new WaiterInvocationHandler(manWaiter);
        //得到代理对象，代理对象就是在目标对象的基础上进行了增强的对象
        Waiter waiter = (Waiter) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        waiter.server();//前面添加“你好”，后面添加“再见”
    }
}
