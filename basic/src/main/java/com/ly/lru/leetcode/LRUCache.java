package com.ly.lru.leetcode;

import java.util.HashMap;

/**
 * liyang 2021-04-15
 * 使用哈希和双向链表实现 LRUCache
 */
public class LRUCache {

    private HashMap<Integer, Node> m; // key 映射到 Node(key, val)
    private DoubleLinkedList cache;   // 双向链表
    private int capacity;             // 容量

    public LRUCache(int capacity) {
        this.capacity = capacity;
        m = new HashMap<>();
        cache = new DoubleLinkedList();
    }

    public int get(int key) {           // O(1)
        if (!m.containsKey(key)) return -1;
        int value = m.get(key).value;
        put(key, value);
        return value;
    }

    public void put(int key, int val) { // O(1)
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

    // 内部双向链表类
    private static class DoubleLinkedList {

        private Node head, tail;
        private int size;

        public void addFirst(Node node) {
            if (head == null) {
                head = tail = node;
            } else {
                head.prev = node;
                node.next = head;
                head = node;
            }
            size++;
        }

        public void remove(Node node) {
            if (node == head && node == tail) {
                head = tail = null;
            } else if (node == tail) {
                tail = node.prev;
                node.prev.next = null;
                node.prev = null;
            }  else if (node == head) {
                head = node.next;
                node.next.prev = null;
                node.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.next = null;
                node.prev = null;
            }
            size--;
        }

        public Node removeLast() {
            Node node = tail;
            remove(tail);
            return node;
        }

        public int size() {
            return size;
        }
    }

    // 内部节点类
    private static class Node {
        public int key, value;
        public Node next, prev;
        public Node (int k, int v) {
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