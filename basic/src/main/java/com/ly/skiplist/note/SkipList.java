package com.ly.skiplist.note;

/**
 * liyang 2021-04-19
 *
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 *
 * update[] => 纵向
 * forward[] => 横向
 *
 * 参考资料：
 * https://www.jianshu.com/p/9d8296562806
 * https://github.com/wangzheng0822/algo/blob/master/java/17_skiplist/SkipList.java
 */
public class SkipList {

    /**
     * 概率因子 0.5 表示每个节点有 1/2 机会被提升，可修改
     */
    private static final float SKIPLIST_P = 0.5f;

    /**
     * 限制最大层数，这里限制为16层，可修改
     */
    private final static int MAX_LEVEL = 16;

    /**
     * 记录当前跳表的层数，1 表示只有原链表，尚未建立索引
     */
    private int levelCount = 1;

    /**
     * 跳表带头节点，作用类似与 dummyHead or 哨兵
     */
    private Node head;

    public SkipList() {
        head = new Node();
    }

    /**
     * 查找：从最高层的 head 开始想尾部查找，每当出现 data > value，降到下一层接力，直到原始链表层（1层，角标为0）
     */
    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forward[i] != null && p.forward[i].data < value) {
                p = p.forward[i];
            }
        }

        if (p.forward[0] != null && p.forward[0].data == value) return p.forward[0];
        return null;
    }

    /**
     * 插入：1、先确定该节点可以最大提升到的层数
     *      2、创建新节点 newNode
     *      3、根据层数创建 update 数组（纵向，初始都指向 head）
     *      4、查找插入位置，更新后 update[i] 对应插入位置的前驱
     *      5、根据层数，从 0 层依次插入新的 newNode，更新 forward 数组
     */
    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node(value, level);

        Node[] update = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            update[i] = head;
        }

        Node p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.forward[i] != null && p.forward[i].data < value) {
                p = p.forward[i];
            }
            update[i] = p;
        }

        for (int i = level - 1; i >= 0; i--) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }

        levelCount = level > levelCount ? level : levelCount;
    }



    /**
     * 删除：1、创建当前层数的 update 数组
     *      2、查找待删除节点的前驱节点 update[i]
     *      3、找到后按照层将前驱节点的下一跳改为下一跳的下一跳
     *      4、更新 levelCount
     */
    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forward[i] != null && p.forward[i].data < value) {
                p = p.forward[i];
            }
            update[i] = p;
        }

        if (p.forward[0] != null && p.forward[0].data == value) {
            int level = p.forward[0].maxLevel;
            for (int i = level - 1; i >= 0; i--) {
                if (update[i].forward[i] != null && update[i].forward[i].data == value) {
                    update[i].forward[i] = update[i].forward[i].forward[i];
                }
            }
        }

        while (levelCount > 1 && head.forward[levelCount - 1] == null) {
            levelCount--;
        }
    }

    /**
     * 因为这里SKIPLIST_P = 0.5f，所以
     *     1/2 的概率返回 1
     *     1/4 的概率返回 2
     *     1/8 的概率返回 3 ...
     */
    private int randomLevel() {
        int level = 1;
        while (level < MAX_LEVEL && Math.random() < SKIPLIST_P ) level++;
        return level;
    }


    /**
     * 打印每一层所有数据。
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SkipList Level Count = ").append(levelCount).append("\n");
        for (int i = levelCount - 1; i >= 0; i--) {
            sb.append("level ").append(i).append(" --> ");
            for (Node p = head; p.forward[i] != null; p = p.forward[i]) {
                sb.append(p.forward[i].data);
                if (p.forward[i].forward[i] != null) {
                    sb.append(",").append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 跳表节点
     */
    public class Node {
        private int data = -1;                            // 存储节点的数值
        private int maxLevel = 0;                         // 跳表当前节点的索引最大层数
        private Node[] forward = new Node[MAX_LEVEL];     // 当前节点所在层的横向下一跳
        Node() {}
        Node(int data, int maxLevel) {
            this.data = data;
            this.maxLevel = maxLevel;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SkipList Node ").append(data);
            sb.append(", maxLevel = ").append(maxLevel).append("\n");
            for (int i = maxLevel - 1; i >= 0; i--) {
                sb.append("level ").append(i).append(" : next node => ");
                if (forward[i] == null) {
                    sb.append("null");
                } else {
                    sb.append(forward[i].data);
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
