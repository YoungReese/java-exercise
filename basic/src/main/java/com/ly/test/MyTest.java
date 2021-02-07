package com.ly.test;



/**
 * liyang 2020-10-09
 * 测试一些特性等
 *
 * ﻿﻿'\ufeff'占一个字符
 */


public class MyTest {
    public static void main(String[] args) throws InterruptedException {

        String str = "\ufeffwww.google.com";

        System.out.print("返回值: ");
        System.out.println(str.substring(1));

        System.out.print("返回值: ");
        System.out.println(str.substring(5));

        System.out.print("返回值: ");
        System.out.println(str.substring(5, 11));


    }
}
