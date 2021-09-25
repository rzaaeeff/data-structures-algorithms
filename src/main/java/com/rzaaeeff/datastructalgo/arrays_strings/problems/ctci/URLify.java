package com.rzaaeeff.datastructalgo.arrays_strings.problems.ctci;

import java.util.ArrayList;
import java.util.List;

public class URLify {
    public static void main(String[] args) {
        if (!urlify2("abc def").equals("abc%20def")) throw new AssertionError();
        if (!urlify2("abc def ghi").equals("abc%20def%20ghi")) throw new AssertionError();
        if (!urlify2("abc def ghi jkl").equals("abc%20def%20ghi%20jkl")) throw new AssertionError();
    }

    static String urlify0(String s) {
        char[] chars = new char[s.length() + 100];
        System.arraycopy(s.toCharArray(), 0, chars, 0, s.length());
        int len = s.length();

        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                System.arraycopy(chars, i + 1, chars, i + 3, len - i - 1);
                len += 2;
                chars[i] = '%';
                chars[i + 1] = '2';
                chars[i + 2] = '0';
            }
        }

        return new String(chars, 0, len);
    }

    static String urlify1(String s) {
        char[] chars = new char[s.length() + 100];
        System.arraycopy(s.toCharArray(), 0, chars, 0, s.length());

        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == ' ') indexes.add(i);
        }

        int last = s.length();
        for (int i = indexes.size() - 1; i >= 0; i--) {
            int index = indexes.get(i);
            int skewedIndex = index + 3 * (i + 1);
            System.arraycopy(chars, index + 1, chars, skewedIndex, last - index - 1);
            chars[skewedIndex - 3] = '%';
            chars[skewedIndex - 2] = '2';
            chars[skewedIndex - 1] = '0';
            last = skewedIndex - 3;
        }

        return new String(chars, 0, s.length() + indexes.size() * 2);
    }

    static String urlify2(String s) {
        char[] chars = new char[s.length() * 3];
        System.arraycopy(s.toCharArray(), 0, chars, 0, s.length());
        chars[s.length()] = '\0';
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < s.length(); i++) {
            if (chars[i] == ' ')
                spaceCount++;
        }
        index = s.length() + spaceCount * 2;
        for (i = s.length() - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                chars[index - 1] = '0';
                chars[index - 2] = '2';
                chars[index - 3] = '%';
                index = index - 3;
            } else {
                chars[index - 1] = chars[i];
                index--;
            }
        }

        return new String(chars, 0, s.length() + spaceCount * 2);
    }
}
