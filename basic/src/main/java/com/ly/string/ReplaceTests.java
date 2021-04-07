package com.ly.string;

/**
 * liyang 2021-04-07
 */
public class ReplaceTests {
    public static void main(String[] args) {
        /**
         * replace 方法是引用透明的
         * replace 方法返回一个新的字符串，用大写的 H 替换所有小写的 h，而不是更新它的this对象，所以它可以被看成函数式的
         */
        System.out.println("hello".replace('h', 'H'));

        /**
         * 重载：public String replace(CharSequence target, CharSequence replacement)
         *
         * Replaces each substring of this string that matches the literal target
         * sequence with the specified literal replacement sequence. The
         * replacement proceeds from the beginning of the string to the end, for
         * example, replacing "aa" with "b" in the string "aaa" will result in
         * "ba" rather than "ab".
         *
         *
         * public final class String
         *     implements java.io.Serializable, Comparable<String>, CharSequence
         */
        System.out.println("hello World, are u ok ?".replace("hello World", "Hello world"));

    }
}
