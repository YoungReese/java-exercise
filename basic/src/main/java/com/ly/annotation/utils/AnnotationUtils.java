package com.ly.annotation.utils;

import com.ly.annotation.createannotation.Gender;
import com.ly.annotation.createannotation.Name;

import java.lang.reflect.Field;

/**
 * liyang 2021-01-15
 * 获取所有注解信息
 */
public class AnnotationUtils {
    public static String getInfo(Class<?> clazz) {
        String result = "";
        // 通过反射获取所有声明的字段
        Field[] declaredFields = clazz.getDeclaredFields();
        // 获取所有字段
        for (Field field : declaredFields) {
            // 获取程序元素上的注解
            if (field.isAnnotationPresent(Name.class)) {
                Name annotation = field.getAnnotation(Name.class);
                String value = annotation.value();
                result += (field.getName() + ": " + value + "\n");
            } else if (field.isAnnotationPresent(Gender.class)) {
                Gender annotation = field.getAnnotation(Gender.class);
                Gender.GenderType value = annotation.gender();
                result += (field.getName() + ": " + value + "\n");
                String value2 = annotation.gender().name();
                result += (field.getName() + ": " + value2 + "\n");
            }
        }
        return result;
    }
}