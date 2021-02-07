package com.ly.thread.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * liyang 2020-12-10
 * 自己的MyUncaughtExceptionHandler
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final String name;
    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常，终止啦" + t.getName());
        System.out.println(name + "捕获了" + t.getName() + "的异常");
    }
}
