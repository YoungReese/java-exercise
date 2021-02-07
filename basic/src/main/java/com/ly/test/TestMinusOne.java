package com.ly.test;

/**
 * liyang 2020-11-17
 * 测试整型-1在计算中的二进制表示形式
 *
 * 结果：
 * 11111111111111111111111111111111
 *
 * 11100000000000000000000000000000
 *
 * Process finished with exit code 0
 */

public class TestMinusOne {
    public static void main(String[] args) {
        final int MINUS_ONE = -1;
        System.out.println(Integer.toBinaryString(MINUS_ONE));

        System.out.println();

        final int COUNT_BITS = Integer.SIZE - 3;
        final int RUNNING = MINUS_ONE << COUNT_BITS;
        System.out.println(Integer.toBinaryString(RUNNING));
    }
}
