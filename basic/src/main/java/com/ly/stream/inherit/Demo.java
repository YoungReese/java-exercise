package com.ly.stream.inherit;

/**
 * liyang 2021-03-19
 * extension不可以调用sayHello，实现接口的类或者子接口不会继承接口中的静态方法。
 *
 * 注意：如果接口中的默认方法不能满足某个实现类需要，那么实现类可以覆盖默认方法，不用加default关键字。
 */
public class Demo {
    public static void main(String[] args) {
        Extension extension = new Extension();
        extension.call();
        extension.sayHi();
    }
}
