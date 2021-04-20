package com.ly.skiplist.note;

import com.ly.skiplist.note.SkipList.Node;

/**
 * https://github.com/andavid/ds-algo-java/blob/master/src/test/java/com/github/andavid/ds/datastructure/skiplist/SkipListTest.java
 */
public class SkipListTests {

    public static void main(String[] args) {
        testSkipList();
    }

    public static void testSkipList() {
        SkipList skipList = new SkipList();
        for (int i = 1; i <= 32; i++) {
            skipList.insert(i);
        }
        System.out.println(skipList);

        int value = 11;
        Node node = skipList.find(value);
        if (node != null) {
            System.out.println(node);
        } else {
            System.out.println(value + " not found");
        }

        System.out.println(" ");
        skipList.delete(value);
        System.out.println(skipList);

        node = skipList.find(value);
        if (node != null) {
            System.out.println(node);
        } else {
            System.out.println(value + " not found");
        }

    }
}
