package com.ly.juc.art.chapter04;

import java.util.concurrent.TimeUnit;

public class Profiler {
    // 第1次get()方法调用时会进行初始化（如果set()方法没有调用），每个线程调用一次
//    // 第1种写法
//    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
//        protected Long initialValue() {
//            return System.currentTimeMillis();
//        }
//    };
//
//    // 第2种写法
//    private static final ThreadLocal<Long> TIME_THREADLOCAL = ThreadLocal.withInitial(() -> System.currentTimeMillis());

    // 第3种写法
    private static final ThreadLocal<Long> TIME_THREADLOCAL = ThreadLocal.withInitial(System::currentTimeMillis);

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws Exception {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
