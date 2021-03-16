package com.ly.stream.functionalinterface;

import java.util.function.Function;

/**
 * liyang 2021-03-16
 * 测试函数式接口 Function => 唯一抽象函数：R apply(T t);
 *                      => 接受一个输入参数，返回一个结果
 *
 * 函数式接口有3种类型的函数：
 *
 * 1、唯一的抽象方法
 *
 * 2、使用default定义普通方法（默认方法），通过对象调用
 *    实现接口后，因为默认方法不是抽象方法，所以可以不重写，但是如果开发需要，也可以重写；
 *    当然如果接口中的默认方法不能满足某个实现类需要，那么实现类可以覆盖默认方法；
 *    签名跟接口default方法一致,但是不能再加default修饰符。
 *
 * 3、使用static定义静态方法，通过接口名调用
 *
 * 被注解 @FunctionInterface 修饰
 *
 * 参考资料：
 * https://blog.csdn.net/z834410038/article/details/77370785
 */
public class FunctionTests {
    public static void main(String[] args) {

        /**
         * jdk 8 之前可以使用匿名内部类实现接口
         */
        Function<Integer, Integer> function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println("execute => function.apply: "+ integer + " => " + (integer + 1));
                return integer + 1;
            }
        };


        /**
         * jdk 8 可以使用 lambda 表达式简化实现
         */
        Function<Integer, Integer> functionLambda = (Integer integer) -> {
            System.out.println("execute => functionLambda.apply: " + integer + " => " + (integer + 1));
            return integer + 1;
        };


        /**
         * 调用 apply 函数
         *
         * execute => function.apply: 2020 => 2021
         * execute => functionLambda.apply: 2021 => 2022
         */
        function.apply(2020);
        functionLambda.apply(2021);


        /**
         * 调用 compose 函数
         *
         * execute => function.apply: 2022 => 2023
         * execute => functionLambda.apply: 2023 => 2024
         */
        functionLambda.compose(function).apply(2022);


        /**
         * 调用 andThen 函数
         *
         * execute => functionLambda.apply: 2023 => 2024
         * execute => function.apply: 2024 => 2025
         */
        functionLambda.andThen(function).apply(2023);


        /**
         * 调用 identity 函数
         * java.util.function.Function$$Lambda$4/883049899@7cc355be
         *
         * 参考资料：https://www.jianshu.com/p/cd694d2d8be5
         */
        System.out.println(Function.identity());

    }
}

/**
* 解析 Function功能型函数式接口
*/
//@FunctionalInterface
//public interface Function<T, R> {
//
//    /**
//     * 接受输入参数，对输入执行所需操作后  返回一个结果。
//     */
//    R apply(T t);
//
//    /**
//     * 返回一个 先执行before函数对象apply方法，再执行当前函数对象apply方法的 函数对象。
//     */
//    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
//        Objects.requireNonNull(before);
//        return (V v) -> apply(before.apply(v));
//    }
//
//    /**
//     * 返回一个 先执行当前函数对象apply方法， 再执行after函数对象apply方法的 函数对象。
//     */
//    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
//        Objects.requireNonNull(after);
//        return (T t) -> after.apply(apply(t));
//    }
//
//    /**
//     * 返回一个执行了apply()方法之后只会返回输入参数的函数对象。
//     */
//    static <T> Function<T, T> identity() {
//        return t -> t;
//    }
//}
