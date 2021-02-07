package com.ly.thread;

public class TestOperation {

    public static void main(String[] args) {
        int nanos = 10;
        int delay = 7;
        int timeLeft = 2;
        nanos -= delay - timeLeft;
        System.out.println(nanos); // 5
        new Object();
    }

}
