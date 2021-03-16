package com.ly.stream.functionalinterface;

import java.util.function.Supplier;

/**
 * liyang 2021-03-16
 * 测试函数式接口 Supplier => 唯一抽象函数：T get();
 *                      => 接受0个输入参数，返回范型T
 *
 * 该函数式接口有且只有一个函数！
 */
public class SupplierTests {

    public static void main(String[] args) {

        Supplier<String> supplier = () -> "hello supplier, 2021";
        /**
         * 测试 get
         *
         * Gets a result.
         * @return a result
         */
        System.out.println(supplier.get()); // hello supplier, 2021
    }

}
