package com.ly.stream.functionalinterface.identity;

import java.util.function.Function;

public class FunctionIdentityTests {
    public static void main(String[] args) {
        /**
         * 调用 identity 函数
         * 及时使用 identity.apply(2021)，identity 调用的也是 return t -> t; 返回2021，不走 apply 逻辑
         */
        Function<Integer, Integer> identity = Function.identity(); // 返回 Function 实例
        System.out.println(identity);
        Integer apply = identity.apply(2021);                   // 返回 2021
        System.out.println(apply);
    }
}
