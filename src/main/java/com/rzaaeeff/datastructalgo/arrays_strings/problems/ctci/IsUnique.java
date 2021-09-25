package com.rzaaeeff.datastructalgo.arrays_strings.problems.ctci;

import com.rzaaeeff.datastructalgo.arrays_strings.impl.HashTable;

import java.util.Arrays;

public class IsUnique {
    public static void main(String[] args) {
        if (!isUnique3("abcdse")) throw new AssertionError();
        if (!isUnique3("abcdefghijklmnopqrstuvwxyz")) throw new AssertionError();
        if (!isUnique3("1234567890")) throw new AssertionError();
        if (isUnique3("abcdefgc")) throw new AssertionError();
        if (isUnique3("abcdefghibc")) throw new AssertionError();
        if (isUnique3("abcdefghibcacdvbn")) throw new AssertionError();
    }

    static boolean isUnique0(String s) {
        HashTable<Integer, Integer> counts = new HashTable<>();

        for (int i = 0; i < s.length(); i++) {
            if (counts.put(s.codePointAt(i), counts.getOrDefault(s.codePointAt(i), 0)+1) > 1)
                return false;
        }

        return true;
    }

    static boolean isUnique1(String s) {
        int[] counts = new int[Character.MAX_CODE_POINT];

        for (int i = 0; i < s.length(); i++) {
            if (++counts[s.codePointAt(i)] > 1)
                return false;
        }

        return true;
    }

    static boolean isUnique2(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i-1])
                return false;
        }

        return true;
    }

    static boolean isUnique3(String s) {
        int checker = 0;

        for (int i = 0; i < s.length(); i++) {
            int charIndex = s.charAt(i) - 'a';
            int singleBitOnPos = 1 << charIndex;

            if ((checker & singleBitOnPos) > 0) {
                return false;
            }

            checker |= singleBitOnPos;
        }

        return true;
    }
}
