package com.ly.exeception;

public class Demo {
    public static void main(String[] args) {
        try {
            pop();
        } catch (NegativeArraySizeException e) {
            System.out.println("pop()方法抛出的异常");
        }
    }

    static void pop() throws NegativeArraySizeException {
        // 定义方法并抛出NegativeArraySizeException异常
        int[] arr = new int[-3];
    }
}
