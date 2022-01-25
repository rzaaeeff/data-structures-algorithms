package com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationWoutDups {
    public static void main(String[] args) {
        System.out.println(getPerms2("aba"));
    }


    private static List<String> getPermutations(String str) {
        if (str.length() == 0) return new ArrayList<>();
        if (str.length() == 1) return Collections.singletonList(str);

        List<String> previousPermutations = getPermutations(str.substring(0, str.length() - 1));
        char ch = str.charAt(str.length() - 1);

        List<String> permutations = new ArrayList<>();
        for (String prevPermutation : previousPermutations) {
            for (int i = 0; i < str.length(); i++) {
                permutations.add(prevPermutation.substring(0, i) + ch + prevPermutation.substring(i));
            }
        }

        return permutations;
    }

    private static List<String> getPerms(String str) {
        if (str == null) return null;

        List<String> permutations = new ArrayList<>();
        if (str.length() == 0) {
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0);
        String rest = str.substring(1);
        List<String> words = getPerms(rest);
        for (String word : words) {
            for (int i = 0; i <= word.length(); i++) {
                String s = word.substring(0, i) + first + word.substring(i);
                permutations.add(s);
            }
        }

        return permutations;
    }

    private static List<String> getPerms2(String str) {
        List<String> permutations = new ArrayList<>();
        getPerms2Helper("", str, permutations);
        return permutations;
    }

    private static void getPerms2Helper(String prefix, String remainder, List<String> permutations) {
        int len = remainder.length();
        if (len == 0) permutations.add(prefix);

        for (int i = 0; i < len; i++) {
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1);
            char c = remainder.charAt(i);
            getPerms2Helper(prefix + c, before + after, permutations);
        }
    }
}
