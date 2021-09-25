package com.rzaaeeff.datastructalgo.arrays_strings.problems.ctci;

import java.util.Arrays;
import java.util.Hashtable;

public class CheckPermutation {
    public static void main(String[] args) {
        if (!checkPermutation1("abc", "cba")) throw new AssertionError();
        if (!checkPermutation1("asdfg", "gdasf")) throw new AssertionError();
        if (!checkPermutation1("1234567890", "0987654321")) throw new AssertionError();
        if (!checkPermutation1("aplq,zm", "m,alpqz")) throw new AssertionError();
        if (checkPermutation1("a", "c")) throw new AssertionError();
        if (checkPermutation1("aplq,zm", "malpqz")) throw new AssertionError();
        if (checkPermutation1("aplq,12", "malpqz")) throw new AssertionError();
        if (checkPermutation1("wd234grve", "lpopmi")) throw new AssertionError();
    }

    private static boolean checkPermutation0(String original, String permutation) {
        if (original.length() != permutation.length()) return false;

        Hashtable<Character, Integer> charCount = new Hashtable<>();

        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            c = permutation.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) - 1);
        }

        for (Integer count : charCount.values()) {
            if (count != 0)
                return false;
        }

        return true;
    }

    private static boolean checkPermutation1(String original, String permutation) {
        if (original.length() != permutation.length()) return false;

        char[] originalArray = original.toCharArray();
        char[] permutationArray = permutation.toCharArray();

        Arrays.sort(originalArray);
        Arrays.sort(permutationArray);

        // not using Arrays.equals because it will do extra length check
        for (int i = 0; i < originalArray.length; i++) {
            if (originalArray[i] != permutationArray[i])
                return false;
        }

        return true;
    }
}
