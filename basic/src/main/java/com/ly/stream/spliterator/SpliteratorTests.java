package com.ly.stream.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * liyang 2021-04-07
 * Spliterator => splittable iterator可分迭代器
 *
 * 拆分过程：参考 Java 8 in action - 7.3
 * 将 Stream 拆分成多个部分的算法是一个递归过程，如图7-6所示。
 * 第一步是对第一个 Spliterator 调用 trySplit，生成第二个 Spliterator。
 * 第二步对这两个 Spliterator 调用 trySplit，这样总共就有了四个 Spliterator。
 * 这个框架不断对 Spliterator 调用 trySplit 直到它返回 null，表明它处理的数据结构不能再分割，
 * 如第三步所示。最后，这个递归拆分过程到第四步就终止了，这时所有的 Spliterator 在调用 trySplit 时都返回了 null。
 *
 */
public class SpliteratorTests {
    public static void main(String[] args) {
        new Spliterator() {
            /**
             * tryAdvance 方法的行为类似于普通的 Iterator，
             * 因为它会按顺序一个一个使用 Spliterator 中的元素，
             * 并且如果还有其他元素要遍历就返回true。
             */
            @Override
            public boolean tryAdvance(Consumer action) {
                return false;
            }

            /**
             * trySplit是专为Spliterator接口设计的，因为它可以把一些元素划出去
             * 分给第二个 Spliterator(由该方法返回)，让它们两个并行处理。
             */
            @Override
            public Spliterator trySplit() {
                return null;
            }

            /**
             * estimateSize 方法估计还剩下多少元素要遍历，因为即使不那么确切，
             * 能快速算出来是一个值也有助于让拆分均匀一点。
             */
            @Override
            public long estimateSize() {
                return 0;
            }

            /**
             * 拆分过程也受 Spliterator 本身的特性影响，而特性是通过 characteristics 方法声明的。
             * 返回的 int 值，代表 Spliterator 本身特性集的编码
             */
            @Override
            public int characteristics() {
                return 0;
            }
        };
    }
}
