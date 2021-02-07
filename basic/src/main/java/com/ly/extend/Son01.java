package com.ly.extend;

public class Son01 implements Father01 {

    @Override
    public void say() {
        System.out.println("This is son01 implements from father01");
    }

    public void sonMethod() {
        System.out.println("Son01 's method");
    }

}
