package com.ly.heap;

/**
 * liyang 2021-01-13
 * 测试小顶堆
 */
public class TestMyPriorityQueue {
    public static void main(String[] args) {
        MyPriorityQueue pq = new MyPriorityQueue();
        int[] array = { 3, 7, 5, 1, 4, 2, 6, 8, 9, 0, 11, 13, 10, 12};
        for (int i = 0; i < array.length; i++) {
            pq.add(array[i]);
            int min = pq.getTop();
            int max = pq.getMax();
            System.out.println("第" + (i + 1) + "插入后：[min = " + min + ", max = " + max + "]\n");
        }
        System.out.println("--------\n");
        pq.getLevelOrder();
        System.out.println("--------\n");
        for (int i = 0; i < array.length; i++) {
            int min = pq.remove();
            int max = pq.getMax() == null ? min : pq.getMax();
            System.out.println("第" + (i + 1) + "移除：[min = " + min + ", max = " + max + "]\n");
        }
    }
}
