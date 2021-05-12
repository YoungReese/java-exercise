package com.ly.algorithm.sort.merge;

/**
 * liyang 2021-05-12
 *
 * 归并排序：顾名思义，就是不断的将两个有序的子序列归并成一个大的序列。
 * 所以核心操作就是有序子列的归并，如果有两个子列，共有 n 个元素，那么归并的时间复杂度是 T(n) = O(n)。
 * 有了归并算法，我们采用易于理解的方式，分而治之的思想，也就是不断地递归将大的序列划分成小的序列，
 * 当序列只有一个元素的时候（自身有序），就是递归终止条件。
 *
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(n)
 * 排序稳定性：稳定排序
 */
public class Merge {

    /**
     * 封装 mSort，方便外部使用统一的接口形式
     */
    public static void mergeSort(int[] arr, int n) {
        int[] aux = new int[n];
        mSort(arr, aux, 0, n - 1);
    }

    /**
     * 递归排序
     */
    public static void mSort(int[] arr, int[] aux, int L, int RightEnd) {
        int mid;
        if (L < RightEnd) {
            mid = L + (RightEnd - L) / 2;
            mSort(arr, aux, L, mid);
            mSort(arr, aux, mid + 1, RightEnd);
            merge(arr, aux, L, mid + 1,RightEnd);
        }
    }

    /**
     * 归并操作：将 arr[L...R - 1] 和 arr[R...RightEnd] 先归并到 aux，然后复制回来
     */
    public static void merge(int[] arr, int[] aux, int L, int R, int RightEnd) {
        int LeftEnd = R - 1;          // arr 左边终点位置
        int i = L;                    // 有序序列的起始位置
        int count = RightEnd - L + 1; // 本次归并后有序元素的个数

        while (L <= LeftEnd && R <= RightEnd) {
            if (arr[L] <= arr[R]) aux[i++] = arr[L++]; // 使用 <= 可以保证排序为稳定排序
            else aux[i++] = arr[R++];
        }

        while (L <= LeftEnd) aux[i++] = arr[L++];

        while (R <= RightEnd) aux[i++] = arr[R++];

        for (i = 0; i < count; ++i, --RightEnd) {
            arr[RightEnd] = aux[RightEnd];
        }
    }


    public static void main(String[] args) {
        int[] arr = { 8, 6, 10, 7, 9, 4, 3, 2, 5, 1 };
        mergeSort(arr, arr.length);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
