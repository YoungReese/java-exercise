package com.ly.algorithm.sort.insertion;

/**
 * liyang 2021-05-12
 *
 * 插入排序：插入排序的算法思想是假定当前待排位置 i 之前的元素都是有序的，并把待排位置元素记为 tmp = arr[i]，
 * 然后从 i 的前一个位置开始比较，如果比当前小，那么该位置的值向后移动一位，直到找到要插入的位置，
 * 循环跳出条件是 i == 0 或者 a[i - 1] <= tmp。然后继续排下一个位置 i + 1，直到数组最后一位，排序过程可类比扑克牌的插入排序过程。
 *
 * 时间复杂度：O(n ^ 2)
 * 排序稳定性：稳定排序
 */
public class Insertion {

    public static void insertionSort(int[] arr, int n) {
        int i, tmp;
        for (int p = 1; p < n; ++p) {
            tmp = arr[p];               // 摸一张牌
            for (i = p; i >= 1 && arr[i - 1] > tmp; --i) {
                arr[i] = arr[i - 1];    // 移动前面的牌到后一个位置
            }
            arr[i] = tmp;               // 新牌落位
        }
    }

    public static void main(String[] args) {
        int[] arr = { 8, 6, 10, 7, 9, 4, 3, 2, 5, 1 };
        insertionSort(arr, arr.length);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
