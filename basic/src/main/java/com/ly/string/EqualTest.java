package com.ly.string;

/**
 * liyang 2021-02-25
 *
 * ==：
 * 在比较对象时比较的是引用地址是否相同；
 * 在比较基本类型时比较的是其内容。
 *
 * equals：
 * 比较的是内容，而不比较其引用。
 *
 * 注意：
 * hashcode相同，其引用地址不一定相同
 */
public class EqualTest {
    public static void main(String[] args) {

//        String s = new String("hello");
        String s = "hello";

        System.out.println(s.hashCode());
        System.out.println("hello".hashCode());

        System.out.println(s.hashCode() == "hello".hashCode() ? "yes" : "no");

        System.out.println("-----------");
        System.out.println(System.identityHashCode(s));
        System.out.println(System.identityHashCode("hello"));

        if (s == "hello") {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
    }

}
