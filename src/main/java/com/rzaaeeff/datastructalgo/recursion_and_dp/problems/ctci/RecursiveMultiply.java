package com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci;

// Write a recursive function to multiply two positive integers without using the *operator.
// You can use addition, subtraction, and bit shifting, but you should minimize the number of those operations.

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RecursiveMultiply {
    public static void main(String[] args) {
//        System.out.println(multiply(-52222, -3));
//        System.out.println(multiplyIterative(-52222, -3));
        long start = System.nanoTime();
        System.out.println(multiplyByHalving(640000, 64));
        long diff1 = System.nanoTime() - start;
        System.out.println("Time elapsed: " + diff1);
        start = System.nanoTime();
        System.out.println(multiplyByHalving_memo(640000, 64));
        long diff2 = System.nanoTime() - start;
        System.out.println("Time elapsed: " + diff2);
        System.out.println("Difference: " + (double) diff1 / (double) diff2 + " times");
    }

    // also works for negatives
    private static int multiply(int a, int b) {
        if (b == 0) return 0;

        int sign = b > 0 ? 1 : -1;
        return multiply(a, b - sign) + sign * a;
    }

    // also works for negatives
    private static int multiplyIterative(int a, int b) {
        int product = 0;

        int sign = b > 0 ? 1 : -1;
        while (b != 0) {
            product += sign * a;
            b -= sign;
        }

        return product;
    }

    // only works for positives
    private static int multiplyByHalving(int a, int b) {
        if (b == 1) return a;

        int product = multiplyByHalving(a, b / 2) + multiplyByHalving(a, b / 2);
        if ((b & 1) == 1) product += a;
        return product;
    }

    private static int multiplyByHalving_memo(int a, int b) {
        int bigger, smaller;
        if (a > b) {
            bigger = a;
            smaller = b;
        } else {
            bigger = b;
            smaller = a;
        }
        return multiplyByHalving_memoHelper(bigger, smaller, new Integer[smaller + 1]);
    }

    private static int multiplyByHalving_memoHelper(int a, int b, Integer[] memo) {
        if (b == 1) return a;

        if (memo[b] == null) {
            int halfProduct = multiplyByHalving_memoHelper(a, b / 2, memo);
            int product = halfProduct + halfProduct;
            if ((b & 1) == 1) product += a;
            memo[b] = product;
        }

        return memo[b];
    }
}
