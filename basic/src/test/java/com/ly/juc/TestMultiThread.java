package com.ly.juc;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestMultiThread {

    private int cnt = 10;

    @Test
    public void test() {
        for (int i = 0; i < cnt; ++i) {
            new Thread(new Runner(),"JUNIT多线程测试").start();
        }
    }

    class Runner implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【当前线程ID】:"+Thread.currentThread().getId());
        }
    }
}
