package com.ly.heap;

import java.util.AbstractQueue;
import java.util.ArrayList;

/**
 * liyang 2021-01-13
 * 实现小顶堆
 */
public class MyPriorityQueue { // extends AbstractQueue

    private final ArrayList<Integer> queue;

    public MyPriorityQueue() {
        queue = new ArrayList<Integer>(10);
    }

    public Integer getMax() {
        int n = queue.size();
        if (n == 0) return null;
        Integer res = Integer.MIN_VALUE;
        for (int i = n / 2; i < n; i++) {
            if (res.compareTo(queue.get(i)) < 0) {
                res = queue.get(i);
            }
        }
        return res;
    }

    public Integer getTop() {
        if (queue.size() == 0) return null;
        return queue.get(0);
    }

    public void add(int e) {
        queue.add(e);
        if (queue.size() == 1) {
            return;
        }
        siftUp(queue.size() - 1, e);
    }

    public Integer remove() {
        int n = queue.size();
        if (n <= 0) return null;
        Integer res = queue.get(0);
        if (n > 1) {
            Integer lastVal = queue.get(n - 1);
            queue.remove(n - 1);
            siftDown(0, lastVal);
        } else {
            queue.remove(0);
        }
        return res;
    }

    public void getLevelOrder() {
        for (int i = 0; i < queue.size(); i++) {
            System.out.println("[" + (i + 1) + ": " + queue.get(i) + "]");
        }
    }

    private void siftUp(int k, Integer x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Integer e = queue.get(parent);
            if (x.compareTo(e) >= 0)
                break;
            queue.set(k, e);
            k = parent;
        }
        queue.set(k, x);
    }

    private void siftDown(int k, Integer x) {
        int n = queue.size();
        int half = n >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Integer c = queue.get(child);
            int right = child + 1;
            if (right < n && c.compareTo(queue.get(right)) > 0)
                c = queue.get(child = right);
            if (x.compareTo(c) <= 0)
                break;
            queue.set(k, c);
            k = child;
        }
        queue.set(k, x);
    }
}
