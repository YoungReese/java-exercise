package com.ly.algorithm.tree.trie;

/**
 * liyang 2021-04-29
 * 实现一个只有小写单词的字典树（前缀树）
 *
 * root 节点存储字典数字入口，不包含字符
 *
 * 参考资料：https://zhuanlan.zhihu.com/p/28891541
 */
public class Trie {
    /**
     * 字典树节点 => 静态内部类实现
     *
     * prefix: 表示以该处节点之前的字符串为前缀的单词数量
     * count: 表示以当前单词结尾的单词数量
     * isWord : 记录从根结点开始到这里，是否构成单词，即使 count == 0 了，也可以通过这个知道这是一个单词
     *        => true  表示是一个单词
     *        => false 表示的是单词中间状态
     * children[i] 表示该孩子节点存储的字符是 i + 'a'
     */
    private static class TrieNode {
        int prefix;
        int count;
        boolean isWord;
        TrieNode[] children;
        public TrieNode() {
            prefix = 0;
            count = 0;
            isWord = false;
            children = new TrieNode[26];
        }
    }

    private TrieNode root; // 字典树根结点，类似于 dummyHead

    public Trie() { root = new TrieNode(); }

    /**
     * insert the word and record the count of the word
     * as well as set isWord = true
     */
    public void insert(String word) {
        if (root == null || word.length() == 0) return;

        TrieNode node = root;
        char[] c = word.toCharArray();
        for (int i = 0, n = word.length(); i < n; ++i) {
            if (node.children[c[i] - 'a'] == null) {
                node.children[c[i] - 'a'] = new TrieNode();
            }
            node = node.children[c[i] - 'a'];
            ++node.prefix;
        }
        node.isWord = true;
        ++node.count;
    }

    /**
     * search the word, return -1 when the word is not exits in the trie
     * or else return the count of this word
     */
    public int search(String word) {
        if (root == null || word.length() == 0) return -1;

        TrieNode node = root;
        char[] c = word.toCharArray();
        for (int i = 0, n = word.length(); i < n; ++i) {
            if (node.children[c[i] - 'a'] == null) return -1;
            node = node.children[c[i] - 'a'];
        }

        return node.count;
    }

    /**
     * search the prefix, return -1 when the prefix is not exits in the trie
     * or else return the count of words start with the prefix
     */
    public int startWith(String prefix) {
        if (prefix.length() == 0) return -1;

        TrieNode node = root;
        char[] c = prefix.toCharArray();
        for (int i = 0, n = prefix.length(); i < n; ++i) {
            if (node.children[c[i] - 'a'] == null) return -1;
            node = node.children[c[i] - 'a'];
        }

        return node.prefix;
    }


    /***************************************** test *****************************************/
    /**
     *
     *
     *              count of "hello" : 3
     *         count of "helloworld" : 1
     * count of word start with "he" : 4
     */
    public static void main(String[] args) {
        Trie trieTree = new Trie();
        trieTree.insert("hello");
        trieTree.insert("hello");
        trieTree.insert("hello");
        trieTree.insert("helloworld");

        System.out.println("             count of \"hello\" : " + trieTree.search("hello"));
        System.out.println("        count of \"helloworld\" : " + trieTree.search("helloworld"));
        System.out.println("count of word start with \"he\" : " + trieTree.startWith("he"));
    }
}
