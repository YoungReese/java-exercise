package com.ly.juc;

import junit.framework.TestCase;

public class TestBoundedBuffer {
    public static void main(String[] args) {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
        TestCase.assertTrue(bb.isEmpty());
        TestCase.assertFalse(bb.isFull());
    }
}
