package com.ly.lru.handwriting.linked;

/**
 * 链表的节点
 */
public class Node {
    public int key, value;
    public Node next, prev;
    public Node (int k, int v) {
        this.key = k;
        this.value = v;
    }
}
