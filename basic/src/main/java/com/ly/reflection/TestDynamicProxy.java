package com.ly.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * liyang 2021-01-14
 * 测试jdk的动态代理类
 *
 * 结果：
 * public abstract void com.ly.reflection.Hello.morning(java.lang.String)
 * Good morning, liyang
 *
 * Process finished with exit code 0
 */
public class TestDynamicProxy {
    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };

        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[] { Hello.class },  // 传入要实现的接口
                handler);                     // 传入处理调用方法的InvocationHandler

        hello.morning("liyang");
    }
}

interface Hello {
    void morning(String name);
}