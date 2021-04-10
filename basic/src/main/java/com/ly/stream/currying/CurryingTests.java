package com.ly.stream.currying;

import java.util.function.DoubleUnaryOperator;

/**
 * liyang 2021-04-09
 *
 * 科里化：它表示一种将一个带有n元组参数的函数转换成n个一元函数链的方法。
 *
 * f(x, y, z) = G(F(x, y) , z) = G(g(z), z) = g(z)
 *
 * 高阶函数：
 * 1、接受至少一个函数作为参数
 * 2、or 返回的结果是一个函数
 */
public class CurryingTests {

    public static void main(String[] args) {
        System.out.println(convertCtoF.applyAsDouble(100)); // 212
    }

    /**
     * 利用科里化模版生成指定的函数：将摄氏温度转换成华氏温度
     */
    private static DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);

    /**
     * 两个参数的科里化模板 => 表示一个高阶函数
     * 传入两个参数，返回一个函数
     * f：转换因子
     * b：基线值
     */
    private static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }
}
