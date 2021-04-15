package com.ly.lru.quickstart;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 使用 Java 自带的双向链表和哈希表实现 LRUCache
 *
 * https://docs.oracle.com/javase/8/docs/api/
 */
public class LRUCache {

    private HashMap<Integer, Pair> m;
    private LinkedList<Pair> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        m = new HashMap<>();
        cache = new LinkedList<>();
    }

    public int get(int key) {             // O(1)
        if (!m.containsKey(key)) return -1;
        Pair pair = m.get(key);
        put(pair.key, pair.value);
        return pair.value;
    }

    public void put(int key, int value) { // O(n)
        if (m.containsKey(key)) {
            cache.remove(m.get(key));     // LinkedList 的 remove 操作是线性的，因此很慢
        } else if (cache.size() == capacity) {
            Pair last = cache.removeLast();
            m.remove(last.key);
        }
        Pair pair = new Pair(key, value);
        cache.addFirst(pair);
        m.put(key, pair);
    }

    private static class Pair {
        public int key, value;
        public Pair (int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
