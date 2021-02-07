package com.ly.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * liyang 2020-12-04
 * 使用工具类Collections中的synchronizedList
 * 对Arraylist进行包装，从而实现线程安全（实质：同步代码块锁定方法）
 *
 * 注意：Arraylist不指定容量，默认是10
 */
public class TestSync {
    public static void main(String[] args) {
        List<Integer> integers = Collections.synchronizedList(new ArrayList<Integer>());
        integers.add(2020);
        System.out.println(integers.get(0));
    }
}
