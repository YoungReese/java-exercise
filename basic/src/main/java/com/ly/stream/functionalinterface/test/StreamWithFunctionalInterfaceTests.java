package com.ly.stream.functionalinterface.test;

/**
 * liyang 2021-03-19
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）
 *
 * 调用逻辑：
 * 编译Lambda表达式生成了一个lambda$main$0私有静态方法，这个静态方法实现了Lambda表达式的逻辑，
 * 这样实现了Lambda表达式，使用invokedynamic指令，运行时调用LambdaMetafactory.metafactory动态的生成内部类，
 * 实现了函数式接口，并在重写函数式接口中的方法，在方法内调用lambda$main$0，内部类里的调用方法块并不是动态生成的，
 * 只是在原class里已经编译生成了一个静态的方法，内部类只需要调用该静态方法。
 *
 *
 * 内部类名字：StreamWithFunctionalInterfaceTests?Lambda$1.class
 * javap -v -p StreamWithFunctionalInterfaceTests?Lambda$1.class看下
 * {
 *   private com.lxs.stream.StreamWithFunctionalInterfaceTests$$Lambda$1();
 *     descriptor: ()V
 *     flags: ACC_PRIVATE
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #10                 // Method java/lang/Object."<init>":()V
 *          4: return
 *
 *   public void print(java.lang.Object);
 *     descriptor: (Ljava/lang/Object;)V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=2, args_size=2
 *          0: aload_1
 *          1: checkcast     #15                 // class java/lang/String
 *          4: invokestatic  #21                 // Method com/lxs/stream/StreamWithFunctionalInterfaceTests.lambda$main$0:(Ljava/lang/String;)V
 *          7: return
 *     RuntimeVisibleAnnotations:
 *       0: #13()
 * }
 *
 * 参考资料：
 * https://juejin.cn/post/6844903890274484231#comment
 */
public class StreamWithFunctionalInterfaceTests {

    public static void main(String[] args) {
        /**
         * 简化过程
         */
        printString("hello lambda 2021", (String s) -> System.out.println(s));
        printString("hello lambda 2021", s -> System.out.println(s));
        printString("hello lambda 2021", System.out::println);
    }

    public static void printString(String s, Print<String> print) {
        print.print(s);
    }

}

@FunctionalInterface
interface Print<T> {
    void print(T t);
}