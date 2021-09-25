package com.rzaaeeff.datastructalgo.arrays_strings.problems.ctci;

public class StringCompression {
    public static void main(String[] args) {
        if (!stringCompression1("aaabbccccaa").equals("a3b2c4a2")) throw new AssertionError();
        if (!stringCompression1("a").equals("a")) throw new AssertionError();
        if (!stringCompression1("abc").equals("abc")) throw new AssertionError();
        if (!stringCompression1("abcaaaaaa").equals("a1b1c1a6")) throw new AssertionError();
        if (!stringCompression1("zzaassaaaaaaaa").equals("z2a2s2a8")) throw new AssertionError();
    }

    private static String stringCompression0(String s) {
        StringBuilder countStr = new StringBuilder();
        int count = 1;

        for (int i = 0; i < s.length() && countStr.length() < s.length(); i++) {
            if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                countStr.append(s.charAt(i));
                countStr.append(count);
                count = 1;
            } else {
                count++;
            }
        }

        return countStr.length() < s.length() ? countStr.toString() : s;
    }

    private static int compressedLength(String s) {
        int compressedLength = 0;
        int count = 1;

        for (int i = 0; i < s.length(); i++) {
            if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(count).length();
                count = 1;
            } else {
                count++;
            }
        }

        return compressedLength;
    }

    private static String stringCompression1(String s) {
        int finalLength = compressedLength(s);
        if (finalLength >= s.length()) return s;

        StringBuilder countStr = new StringBuilder(finalLength);
        int count = 1;

        for (int i = 0; i < s.length(); i++) {
            if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                countStr.append(s.charAt(i));
                countStr.append(count);
                count = 1;
            } else {
                count++;
            }
        }

        return countStr.toString();
    }
}