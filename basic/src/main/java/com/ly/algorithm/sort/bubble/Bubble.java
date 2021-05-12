package com.ly.algorithm.sort.bubble;

/**
 * liyang 2021-05-12
 *
 * 冒泡排序：将数字比作气球，每次比较都涉及相邻的两个元素，将大的下沉一位，第一轮结束最大的元素下沉到最后一位（这一位元素确定），
 * 第二轮继续从头开始做比较，直到倒数第二个位置放入正确的元素，以此类推...（一共执行 n - 1 轮）直到整体有序。
 * 排序过程中，大的元素下沉，小的元素不断上升就如同冒泡一般，故名冒泡排序。
 *
 * 时间复杂度：O(n ^ 2)
 * 排序稳定性：稳定排序
 */
public class Bubble {

    public static void bubbleSort(int[] arr, int n) {
        for (int p = n - 1; p > 0; --p) {
            int flag = 0;                   // 假设本轮没有进行交换
            for (int i = 0; i < p; ++i) {
                if (arr[i] > arr[i + 1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    flag = 1;               // 发生了交换
                }
            }
            if (flag == 0) break;           // 本轮的确没发生交换，说明数组已经完全有序了
        }
    }

    public static void main(String[] args) {
        int[] arr = { 8, 6, 10, 7, 9, 4, 3, 2, 5, 1 };
        bubbleSort(arr, arr.length);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
