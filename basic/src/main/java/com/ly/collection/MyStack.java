package com.ly.collection;

import java.util.LinkedList;

/**
 * liyang 2020-12-04
 * 基于LinkedList实现一个栈
 */

public class MyStack<T> {

    private LinkedList<T> storage = new LinkedList<T>();

    // 入栈
    public void push(T v){
        storage.addFirst(v);
    }

    // 出栈
    public T pop(){
        if (isEmpty()) {
            System.out.print("栈为空，无法出栈 ");
            return null;
        }
        return storage.removeFirst();
    }

    // 窥视栈顶
    public T peek(){
        if (isEmpty()) {
            System.out.print("栈为空，无法获取栈顶元素 ");
            return null;
        }
        return storage.getFirst();
    }

    public boolean isEmpty(){
        return storage.isEmpty();
    }

    public String toString(){
        return storage.toString();
    }

    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>();

        System.out.println(myStack.peek()); // 栈为空，无法获取栈顶元素 null
        System.out.println(myStack.pop());  // 栈为空，无法出栈 null

        myStack.push("world");
        myStack.push("hello");

        System.out.println(myStack.peek()); // hello
        System.out.println(myStack.peek()); // hello

        System.out.println();
        System.out.println(myStack.pop());  // hello
        System.out.println(myStack.pop());  // world
    }

}
