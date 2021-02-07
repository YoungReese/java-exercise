package com.ly.collection;

import java.util.Hashtable;

/**
 * liyang 2020-12-05
 * 远古容器，线程安全的
 * Hashtable不指定容量，默认是11（素数使得哈希冲突减少），加载因子0.75
 *
 * Constructs a new, empty hashtable with a default initial capacity (11)
 * and load factor (0.75).
 */
public class TestHashtable {
    public static void main(String[] args) {

        new Hashtable<>();
    }
}
