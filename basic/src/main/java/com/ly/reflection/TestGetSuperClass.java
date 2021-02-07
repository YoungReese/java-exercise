package com.ly.reflection;

/**
 * liyang 2021-01-14
 * 获取父类的class
 *
 * 结果：
 * class java.lang.Number
 * class java.lang.Object
 * null
 *
 * Process finished with exit code 0
 */
public class TestGetSuperClass {
    public static void main(String[] args) throws Exception {
        Class i = Integer.class;
        Class n = i.getSuperclass();
        System.out.println(n);                  // class java.lang.Number
        Class o = n.getSuperclass();
        System.out.println(o);                  // class java.lang.Object
        System.out.println(o.getSuperclass());  // null
    }
}
