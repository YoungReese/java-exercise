package com.ly.singleton;

/**
 * liyang 2020-12-13
 * 测试单例模式
 */
public class TestSingleton {
    public static void main(String[] args) {
        // 测试Singleton01
        Singleton01 s1_1 = Singleton01.getInstance();
        Singleton01 s1_2 = Singleton01.getInstance();
        System.out.println(s1_1);
        System.out.println(s1_2);
        System.out.println();

        // Singleton02
        Singleton02 s2_1 = Singleton02.getInstance();
        Singleton02 s2_2 = Singleton02.getInstance();
        System.out.println(s2_1);
        System.out.println(s2_2);
        System.out.println();

        // Singleton03
        Singleton03 s3_1 = Singleton03.getInstance();
        Singleton03 s3_2 = Singleton03.getInstance();
        System.out.println(s3_1);
        System.out.println(s3_2);
        System.out.println();

        // Singleton04
        Singleton04 s4_1 = Singleton04.getInstance();
        Singleton04 s4_2 = Singleton04.getInstance();
        System.out.println(s4_1);
        System.out.println(s4_2);
        System.out.println();

        // Singleton05
        Singleton05 s5_1 = Singleton05.getInstance();
        Singleton05 s5_2 = Singleton05.getInstance();
        System.out.println(s5_1);
        System.out.println(s5_2);
        System.out.println();

        // Singleton06
        Singleton06 s6_1 = Singleton06.getInstance();
        Singleton06 s6_2 = Singleton06.getInstance();
        System.out.println(s6_1);
        System.out.println(s6_2);
        System.out.println();

        // Singleton07
        Singleton07 s7_1 = Singleton07.getInstance();
        Singleton07 s7_2 = Singleton07.getInstance();
        System.out.println(s7_1);
        System.out.println(s7_2);
        System.out.println();

        // Singleton08
        Singleton08 s8_1 = Singleton08.getInstance();
        Singleton08 s8_2 = Singleton08.getInstance();
        System.out.println(s8_1);
        System.out.println(s8_2);
        System.out.println();

        // Singleton09
        Singleton09.INSTANCE.whatever();
    }

}
