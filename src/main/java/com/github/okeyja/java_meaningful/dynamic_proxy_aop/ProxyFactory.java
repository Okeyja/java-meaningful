package com.github.okeyja.java_meaningful.dynamic_proxy_aop;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 1、创建代理工厂
 * 2、给工厂设置目标对象、前置增强、后置增强
 * 3、调用 creatProxy() 得到代理对象
 * 4、执行代理对象方法时，先执行前置增强，然后是目标方法，最后是后置增强
 * <p>
 * 其实在 Spring 中的 AOP 的动态代理实现的一个织入器也是叫做 ProxyFactory
 */
@Setter
@Getter
public class ProxyFactory {
    private Object targetObject;       // 目标对象
    private BeforeAdvice beforeAdvice; // 前值增强
    private AfterAdvice afterAdvice;   // 后置增强

    /**
     * 用来生成代理对象
     *
     * @return 代理对象
     */
    public Object creatProxy() {
        // 给出三个参数
        ClassLoader classLoader = this.getClass().getClassLoader();
        //获取当前类型所实现的所有接口类型
        Class<?>[] interfaces = targetObject.getClass().getInterfaces();

        // 在调用代理对象的方法时，会执行这里的内容
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            // 执行前置增强
            if (beforeAdvice != null) {
                beforeAdvice.before();
            }
            Object result = method.invoke(targetObject, args); // 调用目标对象的目标方法
            // 执行后续增强
            if (afterAdvice != null) {
                afterAdvice.after();
            }
            // 返回目标对象的返回值
            return result;
        };

        // 2、得到代理对象
        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }
}
