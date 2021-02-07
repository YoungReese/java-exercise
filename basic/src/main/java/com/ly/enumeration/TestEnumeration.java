package com.ly.enumeration;

/**
 * liyang 2020-12-20
 *
 * 结果：
 * initialize a enum instance
 * initialize a enum instance
 * initialize a enum instance
 * initialize a enum instance
 * front
 * 前面
 *
 * left
 * 左边
 *
 * Process finished with exit code 0
 */
public class TestEnumeration {
    public static void main(String[] args) {
        Direction d = Direction.FRONT;
        System.out.println(d.getName());
        System.out.println(d.getDesc() + "\n");

        d = Direction.LEFT;
        System.out.println(d.getName());
        System.out.println(d.getDesc());
    }
}
