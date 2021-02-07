package com.ly.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * liyang 2020-12-05
 * 演示map的基本用法
 */

public class TestMap {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        System.out.println(map.isEmpty());
        map.put("ly", 18);
        map.put("jack", 20);
        map.put("pony", 21);
        Set<String> keys = map.keySet();
        System.out.println(keys);
//        for (String key : keys) {
//            System.out.println(key);
//        }
        System.out.println(map.get("ly"));
        System.out.println(map.size());
        System.out.println(map.containsKey("jack"));
        map.remove("jack");
        System.out.println(map.containsKey("jack"));


        new ConcurrentHashMap<>();
        new CopyOnWriteArrayList<>();
    }
}
