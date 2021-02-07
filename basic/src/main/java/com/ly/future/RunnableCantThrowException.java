package com.ly.future;

import java.util.concurrent.Callable;

/**
 * liyang 2020-12-04
 * run方法没有返回值，不支持函数标签抛异常
 * call方法范型返回值，支持函数标签抛异常
 */
public class RunnableCantThrowException {

    // 普通的函数可以在函数标签上抛出异常
    public void normalMethod() throws Exception {
        new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try { // runnable中run方法的异常必须try-catch住，不支持直接在函数标签上抛出
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(runnable).start();
    }

}
