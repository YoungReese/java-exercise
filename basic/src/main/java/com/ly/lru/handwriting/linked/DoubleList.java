package com.ly.lru.handwriting.linked;

/**
 * 双向表接口
 */
public interface DoubleList {
    /**
     * 链表头部插入节点 node => O(1)
     */
    void addFirst(Node node);

    /**
     * 删除链表中的 node（一定存在）节点 => O(1)
     */
    void remove(Node node);

    /**
     * 删除链表中最后一个节点并返回 => O(1)
     */
    Node removeLast();

    /**
     * 返回链表的长度 => O(1)
     */
    int size();
}
