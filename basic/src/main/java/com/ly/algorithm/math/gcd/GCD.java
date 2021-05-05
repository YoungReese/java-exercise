package com.ly.algorithm.math.gcd;

/**
 * liyang 2021-05-05
 * 辗转相除法（欧几里得算法） => 求解最大公约数
 *
 * 两个非负整数：gcd(a, b) = gcd(b, a mod b)。
 *
 * 下一次的被除数是这次的除数，下一次的除数是这一次的余数
 */
public class GCD {

    /**
     * greatest common divisor => 最大公约数
     * 以除数和余数反复做除法运算，当余数为 0 时，取当前算式除数为最大公约数
     * 这里递归的除数为 0 对应上一次的余数为 0，被除数对应上一次的除数
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * least common multiple => 最小公倍数
     */
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    /**
     * test
     */
    public static void main(String[] args) {
        int a = 9, b = 6;
        System.out.println("[a = " + a + ", b = " + b + "] 的最大公约数 => " + gcd(a, b));
        System.out.println("[a = " + a + ", b = " + b + "] 的最小公倍数 => " + lcm(a, b));
    }
}
