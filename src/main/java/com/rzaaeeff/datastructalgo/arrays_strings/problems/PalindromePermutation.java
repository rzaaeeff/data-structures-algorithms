package com.rzaaeeff.datastructalgo.arrays_strings.problems;

public class PalindromePermutation {
    public static void main(String[] args) {
        if (palindromePermutation("tacos cat")) throw new AssertionError();
        if (palindromePermutation("Tacot acosi")) throw new AssertionError();
        if (!palindromePermutation("tacossocat")) throw new AssertionError();
        if (!palindromePermutation("taco cat")) throw new AssertionError();
        if (!palindromePermutation("Tact Coa")) throw new AssertionError();
    }

    private static boolean palindromePermutation(String s) {
        int[] count = new int[32];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 'A' || s.charAt(i) > 'z') continue;
            int codePoint = s.charAt(i) - 'A';
            if (codePoint > 31) codePoint -= 32;
            count[codePoint]++;
        }

        boolean foundOddCount = false;

        for (int value : count) {
            if (value % 2 == 1) {
                if (foundOddCount)
                    return false;

                foundOddCount = true;
            }
        }

        return true;
    }
}
