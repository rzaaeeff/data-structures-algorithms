package com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci;

// A child is running up a staircase with n steps and can hop either 1, 2 or 3 steps at a time.
// Implement a method to count how many possible ways the child can run up the stairs.

import java.util.Arrays;

public class TripleStep {
    public static void main(String[] args) {
        System.out.println(memoization(11));
    }

    private static int recursive(int n) {
        if (n == 0) return 1;
        else if (n < 0) return 0;

        return recursive(n - 1) + recursive(n -2) + recursive(n -3);
    }

    private static int memoization(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return memoizationHelper(n, memo);
    }

    private static int memoizationHelper(int n, int[] memo) {
        if (n < 0) return 0;
        else if (n == 0) return 1;
        else if (memo[n] > -1) return memo[n];
        else {
            memo[n] = memoizationHelper(n - 1, memo) +
                    memoizationHelper(n - 2, memo) +
                    memoizationHelper(n - 3, memo);
            return memo[n];
        }
    }
}
