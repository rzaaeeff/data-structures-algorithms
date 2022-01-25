package com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci;

// A magic index in an array A[ 0••• n -1] is defined to be an index such that
// A[i] = i. Given a sorted array of distinct integers, write a method to find
// a magic index, if one exists, in array A.
// Follow up: what if the numbers were distinct?

public class MagicIndex {
    public static void main(String[] args) {
        int[] arr = new int[] {-5, -3, -1, 0, 1, 3, 9, 9, 9, 9};
        System.out.println(magicIndex_distinct(arr));
    }

    private static int magicIndex(int[] arr) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int m = (l + r) / 2;

            if (arr[m] == m) return m;
            else if (arr[m] > m) r = m - 1;
            else l = m + 1;
        }

        return -1;
    }

    private static int magicIndex_distinct(int[] arr) {
        return magicIndex_distinctHelper(arr, 0, arr.length - 1);
    }

    private static int magicIndex_distinctHelper(int[] arr, int start, int end) {
        if (end < start) return -1;

        int midIdx = (start + end) / 2;
        int midVal = arr[midIdx];
        if (midIdx == midVal) return midVal;

        int leftIdx = Math.min(midIdx - 1, midVal);
        int left = magicIndex_distinctHelper(arr, start, leftIdx);
        if (left >= 0) return left;

        int rightIdx = Math.max(midIdx + 1, midVal);
        int right = magicIndex_distinctHelper(arr, rightIdx, end);

        return right;
    }
}
