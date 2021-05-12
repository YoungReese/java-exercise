package com.ly.algorithm.sort.selection;

/**
 * liyang 2021-05-12
 *
 * 选择排序：顾名思义，就是每次循环从数组 [i，n）选择一个最小的元素放入位置 i，一共 n 个元素，需要 n - 1 轮循环。
 *
 * 时间复杂度：O(n ^ 2)
 * 排序稳定性：不稳定排序
 */
public class Selection {

    public static void selectionSort(int[] arr, int n) {
        int tmp;
        for (int i = 0; i < n - 1; ++i) {
            int minPos = i;
            for (int j = i + 1; j < n; ++j) {
                if (arr[j] < arr[minPos]) minPos = j;
            }
            if (i != minPos) {
                tmp = arr[i];
                arr[i] = arr[minPos];
                arr[minPos] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 8, 6, 10, 7, 9, 4, 3, 2, 5, 1 };
        selectionSort(arr, arr.length);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
