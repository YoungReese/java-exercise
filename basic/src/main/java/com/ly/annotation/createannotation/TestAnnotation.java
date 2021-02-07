package com.ly.annotation.createannotation;

import com.ly.annotation.utils.AnnotationUtils;

/**
 * liyang 2021-01-15
 * 测试注解
 *
 * 结果：
 * name: liyang
 * gender: 男
 * gender: Male
 *
 *
 * Process finished with exit code 0
 */
public class TestAnnotation {
    public static void main(String[] args) {
        String info = AnnotationUtils.getInfo(Gender.User.class);
        System.out.println(info);
    }
}
