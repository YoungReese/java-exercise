package com.ly.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * liyang 2020-12-03
 * 测试反射
 * 反射拿到对象和私有方法后，调用私有方法
 *
 * 结果：
 * ly
 * 18
 * 2
 *
 * Process finished with exit code 0
 */
public class TestReflect {

    public static void main(String[] args) {
        try {
            // 反射拿到类和构造器
            Class<?> clazz = Class.forName("com.ly.reflection.User");
            Constructor<?> userConstructor = clazz.getConstructor();
            User user = (User) userConstructor.newInstance();

            // 拿到公有方法并调用
            Method setNameMethod = clazz.getMethod("setName", String.class);
            setNameMethod.invoke(user, "ly");

            user.setAge(18);
            user.setAsserts(2);
            System.out.println(user.getName());   // ly
            System.out.println(user.getAge());    // 18

            // 拿到private方法并调用
            Method getAssertsMethod = clazz.getDeclaredMethod("getAsserts"); // 使用getDeclaredMethod可以拿到私有方法
            getAssertsMethod.setAccessible(true); // 加上这个就可以避免私有方法调用异常，暴力反射
            int asserts = (int) getAssertsMethod.invoke(user);
            System.out.println(asserts);          // 2
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
