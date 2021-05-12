package com.ly.algorithm.sort.quick;

import static com.ly.algorithm.utils.SwapUtil.swap;

/**
 * liyang 2021-05-12
 *
 * 快速排序：快速排序算法并不复杂，一个递归终止条件，一个 pivot 选取方法，一个划分过程，一个治理过程（递归调用），
 * 需要重点关注的是如何选择 pivot 和如何划分两个子集。划分子集的相对比较固定，而如何选择pivot则关系到算法的效率，
 * 最好情况是每次正好中分，从而整体时间复杂度是 O(n log n)。
 *
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(log n)
 * 排序稳定性：不稳定排序
 *
 * 注意：实际数据量小于 100 时，采用插入排序效率更高，没有递归栈等开销
 */
public class Quick {

    public static void quickSort(int[] arr, int n) {
        qSort(arr, 0, n - 1);
    }

    public static void qSort(int[] arr, int left, int right) {
        if (right - left + 1 < 2) return;

        int pivot = arr[left];        // 这里采用最简单方式，将区间中的第一个元素作为 pivot
        swap(arr, left, right);       // 将 pivot 藏在 right 位置
        int i = left - 1, j = right;  // pivot 放在了 right 位置，那么就可以放心的对 [left, right - 1] 进行比较操作了
        while (i < j) {               // 采用前置 ++ 和 -- 可以避免在 if 中再次进行比较
            while (i < right && arr[++i] < pivot) {}
            while (j > 0 && arr[--j] > pivot) {}
            if (i < j) swap(arr, i, j);
        }
        swap(arr, i, right);          // 将 pivot 从 right 位置调换到最终位置

        qSort(arr, left, i - 1);
        qSort(arr, i + 1, right);
    }

    public static void main(String[] args) {
        int[] arr = { 8, 6, 10, 7, 9, 4, 3, 2, 5, 1 };
        quickSort(arr, arr.length);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
