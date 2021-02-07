package com.ly.reflection;


/**
 * liyang 2021-01-14
 * 获取类实现的接口
 *
 * 结果：
 * interface java.io.Serializable
 * interface java.lang.Comparable
 * interface java.lang.CharSequence
 *
 * Process finished with exit code 0
 */
public class TestGetInterfaces {
    public static void main(String[] args) {
        Class s = String.class;
        Class[] is = s.getInterfaces();
        for (Class i : is) {
            System.out.println(i);
        }
    }
}
