package com.rzaaeeff.datastructalgo.arrays_strings.problems.ctci;

public class PalindromePermutation {
    public static void main(String[] args) {
        if (palindromePermutation1("tacos cat")) throw new AssertionError();
        if (palindromePermutation1("Tacot acosi")) throw new AssertionError();
        if (!palindromePermutation1("tacossocat")) throw new AssertionError();
        if (!palindromePermutation1("taco cat")) throw new AssertionError();
        if (!palindromePermutation1("Tact Coa")) throw new AssertionError();
    }

    private static boolean palindromePermutation0(String s) {
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

    private static boolean palindromePermutation1(String s) {
        int checker = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 'A' || s.charAt(i) > 'z') continue;
            int codePoint = s.charAt(i) - 'A';
            if (codePoint > 31) codePoint -= 32;
            int singleBitsOnPos = 1 << codePoint;
            checker ^= singleBitsOnPos;
        }

        return checker == 0 || (checker & (checker - 1)) == 0;
    }
}
