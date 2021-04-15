package com.ly.lru.handwriting.linked;

import java.util.HashMap;

/**
 * liyang 2021-04-15
 * 使用哈希和双向链表实现 LRUCache
 */
public class LRUCache {

    private HashMap<Integer, Node> m; // key 映射到 Node(key, val)
    private DoubleList cache;         // 双向链表
    private int capacity;             // 容量

    public LRUCache(int capacity) {
        this.capacity = capacity;
        m = new HashMap<>();
        cache = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!m.containsKey(key)) return -1;
        int value = m.get(key).value;
        put(key, value);
        return value;
    }

    public void put(int key, int val) {
        if (m.containsKey(key)) {
            cache.remove(m.get(key));
        } else if (cache.size() == capacity) {
            Node last = cache.removeLast();
            m.remove(last.key);
        }
        Node node = new Node(key, val);
        cache.addFirst(node);
        m.put(key, node);
    }
}
