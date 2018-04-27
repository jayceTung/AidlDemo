package com.asuper.aidldemo.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author super
 * @date 2018/4/27
 */
public class MyProcess implements InvocationHandler {
    private Object target;

    public MyProcess(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print("begin");
        method.invoke(target, args);
        System.out.print("end");
        return null;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
