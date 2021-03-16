package com.ly.stream.functionalinterface;

import java.io.File;
import java.io.FileFilter;

/**
 * liyang 2021-03-16
 * 测试函数式接口 FileFilter => 唯一抽象函数：boolean accept(File pathname);
 *  *                     => 接受一个输入参数，返回一个布尔结果
 */
public class FileFilterTests {
    public static void main(String[] args) {

        FileFilter java = (File f) -> f.getName().endsWith(".java");
        File f = new File("Hello.java"); // 一般传入绝对路径
        System.out.println(f.exists());           // false
        System.out.println(f.getName());          // Hello.java
        System.out.println(java.accept(f));       // true

    }
}
