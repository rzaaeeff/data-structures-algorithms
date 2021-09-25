package com.rzaaeeff.datastructalgo.arrays_strings.problems.leetcode;

import java.util.Arrays;

public class MultiplyStrings {
    public static void main(String[] args) {
        if (!add("2", "3").equals("5")) throw new AssertionError();
        if (!add("123", "456").equals("579")) throw new AssertionError();
        if (!multiply("123", "456").equals("56088")) throw new AssertionError();
        if (!multiply("3", "990").equals("2970")) throw new AssertionError();
    }

    private static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int rem = 0;

        String big = num1.length() > num2.length() ? num1 : num2;
        String small = num1.length() > num2.length() ? num2 : num1;

        StringBuilder[] stringBuilders = new StringBuilder[big.length()];
        for (int i = 0; i < stringBuilders.length; i++) stringBuilders[i] = new StringBuilder();

        for (int i = big.length() - 1; i >= 0; i--) {
            for (int j = small.length() - 1; j >= 0 || rem > 0; j--) {
                int d1 = big.charAt(i) - '0';
                int d2 = j >= 0 ? small.charAt(j) - '0': 0;

                int res = d1 * d2 + rem;
                rem = 0;
                if (res > 9) {
                    rem = res / 10;
                    res %= 10;
                }

                stringBuilders[i].insert(0, res);
            }

            char[] zeros = new char[big.length() - i - 1];
            Arrays.fill(zeros, '0');
            stringBuilders[i].append(zeros);
        }

        String result = stringBuilders[0].toString();

        for (int i = 1; i < stringBuilders.length; i++) {
            result = add(result, stringBuilders[i].toString());
        }

        return result;
    }

    private static String add(String num1, String num2) {
        if (num1.equals("0") && num2.equals("0")) return "0";

        StringBuilder sb = new StringBuilder(num1.length() + num2.length());
        int rem = 0;
        int i = num1.length() - 1, j = num2.length() - 1;

        while (i >= 0 || j >= 0 || rem > 0) {
            int d1 = i >= 0 ? num1.charAt(i) - '0': 0;
            int d2 = j >= 0 ? num2.charAt(j) - '0': 0;

            int res = d1 + d2 + rem;
            rem = 0;
            if (res > 9) {
                rem = res / 10;
                res %= 10;
            }

            sb.insert(0, res);
            i--;
            j--;
        }

        return sb.toString();
    }
}
