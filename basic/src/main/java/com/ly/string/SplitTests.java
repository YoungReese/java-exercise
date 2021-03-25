package com.ly.string;

import java.util.ArrayList;
import java.util.List;

/**
 * liyang 2021-03-25
 * 测试 split => 挺好用的
 */
public class SplitTests {
    public static void main(String[] args) {
        List<String> infos = new ArrayList<>();
        infos.add("liyang,18");
        infos.add("young,   18   ");

        for (String info : infos) {
            String name = info.split(",")[0];
            String age = info.split(",")[1].trim();
            System.out.println("name: " + name + "\n" + "age: " + age);
        }

    }
}
