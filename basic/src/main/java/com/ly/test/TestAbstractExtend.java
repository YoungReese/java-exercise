package com.ly.test;

/**
 * liyang 2020-11-28
 *
 * 继承的抽象方法必须重写
 *
 * 继承成的普通方法可以选择是否重写
 */
public class TestAbstractExtend extends AbstractFatherClass {
    // 下面两个必须重写
    @Override
    public void sayHi() {

    }

    @Override
    void sayHiShared() {

    }

    // 下面两个可以选择重写
    @Override
    public void read() {
        System.out.println("非共享读");
    }

    @Override
    void readShared() {
        System.out.println("共享读");
    }
}


abstract class AbstractFatherClass {
    abstract public void sayHi();
    abstract void sayHiShared();

    public void read() {}
    void readShared() {}
}