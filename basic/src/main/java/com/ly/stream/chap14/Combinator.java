package com.ly.stream.chap14;

import java.util.function.Function;

/**
 * liyang 2021-04-10
 * 结合器，即接受两个或多个方法(函数)做参数且返回结果是另一个函数的方法
 */
public class Combinator {
    public static void main(String[] args) {

        /**
         * t -> (2 * 2 * 2) * t
         */
        System.out.println(repeat(3, (Integer t) -> 2 * t).apply(10));

        /**
         * 计算 a ^ b  =>  5 ^ 4 = 625
         *
         * 推导：
         * f(4) = f * g(3)
         * f(3) = f * g(2)
         * f(2) = f * g(1)
         * f(1) = f * g(0)
         * f(0) = a * t
         *
         * in this code: f is equals g to represent the recursive
         * so, f(4) = a ^ b * t
         */
        int a = 5, b = 4;
        Integer res = repeat(b, (Integer t) -> a * t).apply(10);
        System.out.println(res);
    }

    static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? t -> t : compose(f, repeat(n - 1, f));
    }

    static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return t -> g.apply(f.apply(t));
    }
}
