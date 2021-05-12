package com.ly.algorithm.sort.heap;

/**
 * liyang 2021-05-12
 *
 * 堆排序：建立一个大顶堆，每次将堆顶元素与当前需要调节的最后一个元素互换，这样最大元素调整到最终位置，
 * 现在只要对前面的元素调用 percDown 操作即可，以此类推...直到数组整体有序。
 *
 * 时间复杂度：O(n log n)
 * 排序稳定性：不稳定排序
 */
public class Heap {

    public static void heapSort(int[] arr, int n) {
        // build heap
        for (int i = n / 2 - 1; i >= 0; --i) {
            percDown(arr, i, n);
        }
        // delete max
        for (int i = n - 1; i > 0; --i) {
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;
            percDown(arr, 0, i);
        }
    }

    private static void percDown(int[] arr, int p, int n) {
        int parent, child;
        int x = arr[p];
        for (parent = p; parent * 2 + 1 < n; parent = child) {
            child = parent * 2 + 1;
            if (child != n - 1 && arr[child] < arr[child + 1]) child++;
            if (x > arr[child]) break;
            else arr[parent] = arr[child];
        }
        arr[parent] = x;
    }

    public static void main(String[] args) {
        int[] arr = { 8, 6, 10, 7, 9, 4, 3, 2, 5, 1 };
        heapSort(arr, arr.length);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
