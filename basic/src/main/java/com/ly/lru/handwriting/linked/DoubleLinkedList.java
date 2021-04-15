package com.ly.lru.handwriting.linked;

/**
 * 双向链表实现
 */
public class DoubleLinkedList implements DoubleList {

    private Node head, tail;
    private int size;

    @Override
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

    @Override
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

    @Override
    public Node removeLast() {
        Node node = tail;
        remove(tail);
        return node;
    }

    @Override
    public int size() {
        return size;
    }
}
