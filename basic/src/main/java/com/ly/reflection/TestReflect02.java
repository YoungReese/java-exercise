package com.ly.reflection;

/**
 * liyang 2020-12-25
 * 获取Class对象的方式
 *
 * 1、Class.forName("全类名")  （全类名包含了包名，不需要导包）
 * 2、类名.class               （需要导包，否则只在本包下查找）
 * 3、对象.getClass()          （需要此类对象）
 *
 * 结果：
 * class com.ly.reflection.User
 * class com.ly.reflection.User
 * class com.ly.reflection.User
 * 1625635731
 * 1625635731
 * 1625635731
 *
 * Process finished with exit code 0
 *
 * 结论：
 * 同一个字节码文件（*.class）在一次程序运行过程中，只会被加载一次，
 * 不论通过哪一种方式获取的Class对象都是同一个！
 */
public class TestReflect02 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 方法1：Class.forName("全类名")
        Class<?> clazz = Class.forName("com.ly.reflection.User");
        System.out.println(clazz);

        // 方法2：类名.class
        Class<?> clazz02 = User.class;
        System.out.println(clazz02);

        // 对象.getClass()
        User user = new User();
        Class<?> clazz03 = user.getClass();
        System.out.println(clazz03);

        // 打印hashcode
        System.out.println(clazz.hashCode());
        System.out.println(clazz02.hashCode());
        System.out.println(clazz03.hashCode());
    }
}
