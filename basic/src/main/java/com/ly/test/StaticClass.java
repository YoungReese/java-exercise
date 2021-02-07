package com.ly.test;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class StaticClass {
    public static long OUTER_DATE = System.currentTimeMillis();

    public StaticClass() {
        System.out.println("外部类构造函数时间：" + System.currentTimeMillis());
    }

    static {
        System.out.println("外部类静态块加载时间：" + System.currentTimeMillis());
    }


    public static class InnerStaticClass {
        public static long INNER_STATIC_DATE = System.currentTimeMillis();
        static {
            System.out.println("静态内部类静态块加载时间：" + System.currentTimeMillis());
        }
    }

    class InnerClass {
        public long INNER_DATE = 0;
        public InnerClass() {
            INNER_DATE = System.currentTimeMillis();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //
        StaticClass outer = new StaticClass();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("外部类静态变量加载时间：" + outer.OUTER_DATE);

        //
        System.out.println();
        System.out.println("----");
        System.out.println("非静态内部类加载时间"+outer.new InnerClass().INNER_DATE);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("非静态内部类加载时间"+outer.new InnerClass().INNER_DATE);

        //
        System.out.println();
        System.out.println("----");
        System.out.println("静态内部类加载时间："+InnerStaticClass.INNER_STATIC_DATE);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("静态内部类加载时间："+InnerStaticClass.INNER_STATIC_DATE);
    }


    @Test
    public void test() throws InterruptedException {
        System.out.println("静态内部类加载时间："+ StaticClass.InnerStaticClass.INNER_STATIC_DATE);
    }
}