package com.ly.stream;

import lombok.*;

import java.util.List;
import java.util.Vector;
import java.util.function.Function;

/**
 * liyang 2021-03-16
 * ? super V, ? extends T 理解
 *
 * List<? super T>    当容器用时，能容纳T本身及T的子类，但无法容纳T的超类。用来向函数传递参数时，只能传递T及T的超类
 * List<? extends T>  当容器用时，只能容纳null，没什么用处。用来向函数传递参数时，只能传递T及T的子类，不能传递T的超类
 *
 * 参考资料：https://blog.csdn.net/qq_16605855/article/details/85278267
 */
public class SuperVAndExtendsT {

    public static void main(String[] args) {
        (new SuperVAndExtendsT()).act();
    }

    /**
     * Third -> Second -> First -> Base
     */
    public void act() {
        Vector<First> vs = new Vector<>();
        // vs.add(new Base());      // 错误，First容器只能装First及其子类，不能装First的超类，
        vs.add(new Second());

        List<? super Second> vSuper = new Vector<First>(); //可以容纳本类及子类，但无法容纳超类
//        vSuper.add(new Base());   // 错误vSuper只是Vector<First>的引用，First的容器只能装First及其子类，不能装它的超类
//        vSuper.add(new First());  // 错误
        vSuper.add(new Second());   // 正确
        vSuper.add(new Third());    // 正确

        checkSuper(new Vector<First>());   // 可以传递超类、本类参数，但无法传递子类参数
        checkSuper(new Vector<Second>());
//        checkSuper(new Vector<Third>()); // 错误

        List<? extends Second> vExtends = new Vector<Second>(); // 不能容纳任何子类、本类、超类
//        vExtends.add(new First()); // 错误
//        vExtends.add(new Second());// 错误
//        vExtends.add(new Third()); // 错误
        vExtends.add(null);          // 正确，但是没用

        // checkExtends(new Vector<First>()); // 错误，无法传递超类参数
        checkExtends(new Vector<Second>());   // 能够传递本类及子类参数
        checkExtends(new Vector<Third>());


        /**
         * 创建一个second
         */
        Second second = new Second();
        second.setName("apple");
        second.setId("11");
        second.setKind("11");

        /**
         * 重写 apply 函数
         * 传入 Second 返回 First
         */
        final Function<Second, First> firstBaseFunction = t -> (First) t;

        /**
         * 重写 apply 函数
         * 传入 First 返回 Base
         *
         * Second{id='11', name='apple', kind='11'}
         */
        final Function<First, Base> firstBaseFunction1 = t -> (Base) t;
        final Base apply1 = firstBaseFunction1.compose(firstBaseFunction).apply(second);
        System.out.println(apply1);

        /**
         * 可以使用 Function.identity(); 替代
         *
         * Second{id='11', name='apple', kind='11'}
         */
//        final Function<Second, Second> firstBaseFunction2 = t -> t; // 与下一句等价
        final Function<Second, Second> firstBaseFunction2 = Function.identity();
        // second extends first
        final Base apply2 = firstBaseFunction1.compose(firstBaseFunction2).apply(second);
        System.out.println(apply2);


        /**
         * 重写 apply 函数
         * 传入 Base 返回 First
         *
         * Second{id='11', name='apple', kind='11'}
         */
        final Function<Base, First> firstBaseFunction3 = t -> (First) t;
        final Base apply3 = firstBaseFunction1.compose(firstBaseFunction3).apply(second);
        System.out.println(apply3);
    }


    public void checkSuper(List<? super Second> a) {}

    public void checkExtends(List<? extends Second> a) {}


    @NoArgsConstructor
    @Getter
    @Setter
    public class Base {
        @Override
        public String toString() {
            return "Base{" +
                    "id='" + id + '\'' +
                    '}';
        }

        String id;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public class First extends Base {
        @Override
        public String toString() {
            return "First{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        String name;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public class Second extends First {
        @Override
        public String toString() {
            return "Second{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", kind='" + kind + '\'' +
                    '}';
        }

        String kind;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public class Third extends Second {
        @Override
        public String toString() {
            return "Third{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", kind='" + kind + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }

        String color;
    }

}
