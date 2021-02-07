package com.ly.instance;

import com.ly.test.TestFixedThreadPool;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * liyang 2020-12-23
 * instanceof 是 Java 的保留关键字。
 * 它的作用是测试它左边的对象是否是它右边的类的实例，返回 boolean 的数据类型。
 */
public class TestInstance {

    public static void displayObjectClass(Object o) {
        if (o instanceof Object)
            System.out.println("对象是 Object 类的实例");
        if (o instanceof Vector)
            System.out.println("对象是 java.util.Vector 类的实例");
        else if (o instanceof ArrayList)
            System.out.println("对象是 java.util.ArrayList 类的实例");
        else
            System.out.println("对象是 " + o.getClass() + " 类的实例");
    }

    public static String displayExecutorClass(Executor executor) {
        return (executor instanceof AbstractExecutorService) ?
                ("AbstractExecutorService: executor")  : "null";
    }

    public static void main(String[] args) {
        Object testObject = new ArrayList();
        displayObjectClass(testObject);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        System.out.println(displayExecutorClass(executor));
    }

}
