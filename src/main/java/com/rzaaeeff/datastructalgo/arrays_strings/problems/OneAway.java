package com.rzaaeeff.datastructalgo.arrays_strings.problems;

public class OneAway {
    public static void main(String[] args) {
        if (!oneAway2("pale", "ple")) throw new AssertionError();
        if (!oneAway2("pales", "pale")) throw new AssertionError();
        if (!oneAway2("pale", "bale")) throw new AssertionError();
        if (oneAway2("pale", "bake")) throw new AssertionError();
    }

    private static int codePoint(char c) {
        int codePoint = c - 'A';
        if (codePoint > 31) codePoint -= 32;
        return codePoint;
    }

    private static boolean oneAway0(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1)
            return false;

        int[] count = new int[32];

        for (int i = 0; i < s1.length(); i++) {
            count[codePoint(s1.charAt(i))]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            count[codePoint(s2.charAt(i))]--;
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

    private static boolean oneAway1(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1)
            return false;

        int checker = 0;

        for (int i = 0; i < s1.length(); i++) {
            int singleBitsOnPos = 1 << codePoint(s1.charAt(i));
            checker |= singleBitsOnPos;
        }

        for (int i = 0; i < s2.length(); i++) {
            int singleBitsOnPos = 1 << codePoint(s2.charAt(i));
            checker &= ~singleBitsOnPos;
        }

        return checker == 0 || (checker & (checker - 1)) == 0;
    }

    static boolean oneAway2(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1)
            return false;

        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;

        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (foundDifference) return false;
                foundDifference = true;

                if (s1.length() == s2.length()) index1++;
            } else {
                index1++;
            }

            index2++;
        }

        return true;
    }
}
