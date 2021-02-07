package com.ly.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflect03 {
    public static void main(String[] args) throws Exception {
        // 反射拿到类
        Class<?> clazz = Class.forName("com.ly.reflection.User");

        // 拿到所有的字段并打印其名字
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field); // field.getName()
        }
        System.out.println();

        // 拿到所有的构造函数并打印名字
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);//打印出Person类中所有的构造方法
        }
        System.out.println();

        // 拿到所有方法并打印其名字，参数和返回值
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
