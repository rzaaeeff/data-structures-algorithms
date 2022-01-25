package com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci;

public class Coins {
    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(countOfWays(43));
        long end = System.nanoTime();
        System.out.println("Time elapsed: " + (end - start));

        start = System.nanoTime();
        System.out.println(makeChange(43));
        end = System.nanoTime();
        System.out.println("Time elapsed: " + (end - start));
    }

    private static int countOfWays(int amount) {
        if (amount <= 0) return 1;
        int[] coins = {25, 10, 5, 1};
        int[][] memo = new int[amount + 1][coins.length];
        return countOfWays(coins, amount, 0, memo);
    }

    private static int countOfWays(int[] coins, int amount, int idx, int[][] memo) {
        if (amount < 0) return 0;
        if (memo[amount][idx] > 0) return memo[amount][idx];
        if (amount == 0) return 1;

        int ways = 0;
        for (int i = idx; i < coins.length; i++) {
            ways += countOfWays(coins, amount - coins[i], i, memo);
        }

        memo[amount][idx] = ways;
        return ways;
    }

    private static int makeChange(int n) {
        int[] denoms = {25, 10, 5, 1};
        int[][] map = new int[n + 1][denoms.length];
        return makeChange(n, denoms, 0, map);
    }

    private static int makeChange(int amount, int[] denoms, int index, int[][] map) {
        if (map[amount][index] > 0) return map[amount][index];
        if (index >= denoms.length - 1) return 1;

        int denomAmount = denoms[index];
        int ways = 0;
        for (int i = 0; i * denomAmount <= amount; i++) {
            int remainingAmount = amount - i * denomAmount;
            ways += makeChange(remainingAmount, denoms, index + 1, map);
        }

        map[amount][index] = ways;
        return ways;
    }
}
