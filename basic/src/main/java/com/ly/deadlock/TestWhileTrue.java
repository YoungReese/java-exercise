package com.ly.deadlock;

/**
 * todo:并发编程实战
 */
public class TestWhileTrue {
    public static void main(String[] args) {
        while (true) {
            System.out.println("hi");
        }
    }
}
