package com.ly.stream.lambda;


/**
 * liyang 2021-03-26
 * lambda 语法说明
 */
public class LambdaSyntax {
    public static void main(String[] args) {
        /**
         * 不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误
         *
         * Variable used in lambda expression should be final or effectively final
         * 表示 lambda 中引用外部变量，不能改变它的值，并不是说外部变量必须被 final 修饰 <=>
         * lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
         */
        String salutation = "Hello ";
        int outsideLambda = 2021;
        GreetingService greetService1 = message -> {
//            salutation = "Hi "; // Variable used in lambda expression should be final or effectively final
//            outsideLambda = 2022;
            System.out.println(salutation + message + " => " + outsideLambda);
        };
        greetService1.sayMessage("World!");
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }
}
