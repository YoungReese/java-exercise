package com.ly.test;

/**
 * liyang 2021-01-11
 * t = (t != (t = tail)) ? t : head
 * 等价于
 * if (t != tail) {
 *     t = tail;
 * } else {
 *     t = head;
 * }
 *
 * 解释：先是 t != tail 比较，之后 t = tail ，因为关系运算符的优先级大于赋值运算符的优先级且关系运算符在左边
 */
public class TestHeadTail {
    public static void main(String[] args) {
        int head = 1;
        int tail = 2;
        int t = tail;
        for (int i = 0; i < 4; i++) {
            System.out.println(t = (t != (t = tail)) ? t : head);
        }

        System.out.println("---\n");

        head = 1;
        int s = 2;
        System.out.println((s = head) == s ? "yes" : "no"); // yes

        head = 1;
        s = 2;
        System.out.println(s == (s = head) ? "yes" : "no"); // no
    }
}
