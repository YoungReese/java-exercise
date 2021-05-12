package com.ly.algorithm.sort.shell;

/**
 * liyang 2021-05-12
 *
 * 希尔排序：前面介绍过插入排序，要提高算法效率，我们必须一次消去不止 1 个逆序对，比如交换相隔较远的 2 个元素，
 * 所以这里引入希尔排序，每次交换的两个元素相隔较远，所以一次交换消除不止一对逆序对，会加快排序过程。
 *
 * 时间复杂度：最坏情况 O(n ^ 2) => 一般情况 O(n ^ d) (d < 2)
 * 排序稳定性：不稳定排序
 */
public class Shell {

    /* 这里只列出一小部分增量 */
    private static int[] Sedgewick= { 929, 505, 209, 109, 41, 19, 5, 1, 0 };

    public static void shellSort(int[] arr, int n) {
        int si, i, tmp;
        for (si = 0; Sedgewick[si] >= n; ++si) {} // 初始的增量 Sedgewick[si] 不能超过待排序列长度
        for (int d = Sedgewick[si]; d > 0; d = Sedgewick[++si]) { // 每次更改间隔 d 的大小
            for (int p = d; p < n; ++p) {         // 将原来插入排序数字1全部改为间隔 d
                tmp = arr[p];
                for (i = p; i >= d && arr[i - d] > tmp; i -= d) {
                    arr[i] = arr[i - d];
                }
                arr[i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 8, 6, 10, 7, 9, 4, 3, 2, 5, 1 };
        shellSort(arr, arr.length);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
