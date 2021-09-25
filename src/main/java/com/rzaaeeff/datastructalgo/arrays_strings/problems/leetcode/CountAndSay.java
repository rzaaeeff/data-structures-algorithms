package com.rzaaeeff.datastructalgo.arrays_strings.problems.leetcode;

public class CountAndSay {
    public static void main(String[] args) {
        if (!countAndSay(1).equals("1")) throw new AssertionError();
        if (!countAndSay(2).equals("11")) throw new AssertionError();
        if (!countAndSay(3).equals("21")) throw new AssertionError();
        if (!countAndSay(4).equals("1211")) throw new AssertionError();
        if (!countAndSay(5).equals("111221")) throw new AssertionError();
        if (!countAndSay(6).equals("312211")) throw new AssertionError();
        if (!countAndSay(7).equals("13112221")) throw new AssertionError();
        if (!countAndSay(8).equals("1113213211")) throw new AssertionError();
    }

    private static String countAndSay(int n) {
        return countAndSayRec(n);
    }

    private static String countAndSayRec(int n) {
        if (n == 1) return "1";

        String num = countAndSayRec(n - 1);
        char last = num.charAt(0);

        StringBuilder sb = new StringBuilder();
        int counter = 0;

        for (char digit : num.toCharArray()) {
            if (digit == last) counter++;
            else {
                sb.append(counter);
                sb.append(last);
                last = digit;
                counter = 1;
            }
        }

        if (counter > 0) sb.append(counter).append(last);

        return sb.toString();
    }
}
