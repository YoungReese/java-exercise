package com.ly.thread;

public class TestThreadLambda {

    public static void sayHello() {
        System.out.println("hello");
    }

    public static void say() {
        System.out.println("emmmc");
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("hi");
        }).start();

        new Thread(() -> {
            sayHello();
        }).start();

        new Thread(TestThreadLambda::say).start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }.start();

        new Thread( () -> {
            System.out.println("hello lambda");
        } ) {
            @Override
            public void run() {
                super.run();
            }
        }.start();

    }

}
