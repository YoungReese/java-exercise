package com.ly.thread.commom;

/**
 * liyang 2020-12-10
 *
 * public final synchronized void setName(String name) {
 *     checkAccess();
 *     if (name == null) {
 *         throw new NullPointerException("name cannot be null");
 *     }
 *
 *     this.name = name;
 *     if (threadStatus != 0) {
 *         setNativeName(name);
 *     }
 * }
 *
 * // Java thread status for tools, initialized to indicate thread 'not yet started'
 * private volatile int threadStatus = 0;
 *
 */
public class TestThreadSetName {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        System.out.println(thread.getName());
        thread.setName("ly");
        System.out.println(thread.getName());
    }
}
