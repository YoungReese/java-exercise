package com.ly.test;

public class Dup {

    public static void main(String[] args) {
        int[] a = {};
        int[] b = {};
        System.out.println(get(a, b));
    }

    public static boolean get(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int i = 0;
        int j = 0;

        while (i < m && j < n) {
            if (a[i] == b[j]) {
                i = killDup(a, i, a[i], m);
                j = killDup(b, j, b[j], n);
            } else if (a[i] > b[j]) {
                j++;
            } else if (a[i] < b[j]) {
                return false;
            }
        }

        if (i == m && j <= n) return true;
        return false;
    }

    public static int killDup(int[] arr, int p, int val, int limit) {
        while (p < limit && arr[p] == val) {
            p++;
        }
        return p;
    }

}
