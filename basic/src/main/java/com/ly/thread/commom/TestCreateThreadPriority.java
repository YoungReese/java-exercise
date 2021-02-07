package com.ly.thread.commom;

/**
 * liyang 2020-12-10
 * 测试父线程创建的子线程，子线程的优先级会继承父线程的优先级
 *
 * 结果：
 * 5
 * 10
 * 10
 *
 * Process finished with exit code 0
 *
 * 注：优先级有10个级别，默认是5
 * public final static int MIN_PRIORITY = 1;
 * public final static int NORM_PRIORITY = 5;
 * public final static int MAX_PRIORITY = 10;
 *
 * 但是程序的设计不应该依赖于优先级，因为优先级只是建议性质的，os底层的具体调度不受控制
 * 1、不用操作系统不一样，比如win只有7个优先级别（linux忽略优先级，solaris 2^31），
 * 使用6个来对应java中的优先级，从而出现java两个优先级对应win系统中的一个优先级
 * 2、优先级会被操作系统改变
 */
public class TestCreateThreadPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getPriority()); // 5

        Thread.currentThread().setPriority(10);
        System.out.println(Thread.currentThread().getPriority()); // 10

        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getPriority())); // 10
        thread.start();

    }
}
