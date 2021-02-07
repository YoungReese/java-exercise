package com.ly.test;

public class TestFinally {

    public static void main(String[] args) {
        int i = 0;
        while ( i++ < 5) {
            System.out.println("加锁成功");
            try {
                System.out.println("执行任务");
            } finally {
                System.out.println("解锁");
                System.out.println();
            }
        }
    }
}
