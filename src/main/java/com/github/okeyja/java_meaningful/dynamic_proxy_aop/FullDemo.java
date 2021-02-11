package com.github.okeyja.java_meaningful.dynamic_proxy_aop;

/**
 * https://www.jianshu.com/p/aaeb2355ec5c
 */
public class FullDemo {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(); // 创建工厂
        proxyFactory.setTargetObject(new ManWaiter());  // 设置目标对象
        // 设置前置增强
        proxyFactory.setBeforeAdvice(() -> System.out.println("客户你好"));
        // 设置后置增强
        proxyFactory.setAfterAdvice(() -> System.out.println("客户再见"));
        Waiter waiter = (Waiter) proxyFactory.creatProxy();
        waiter.server();
    }
}
