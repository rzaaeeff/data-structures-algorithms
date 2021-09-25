package com.rzaaeeff.datastructalgo.arrays_strings.problems.leetcode;

import java.math.BigInteger;

public class AddBinary {
    public static void main(String[] args) {
        if (!addBinary1("11", "1").equals("100")) throw new AssertionError();
        if (!addBinary1("1010", "1011").equals("10101")) throw new AssertionError();
        if (!addBinary1("1111", "1111").equals("11110")) throw new AssertionError();
    }

    private static String addBinary0(String a, String b) {
        return new BigInteger(a, 2).add(new BigInteger(b, 2)).toString(2);
    }

    private static String addBinary1(String a, String b) {
        int carry = 0;
        int ia = a.length() - 1, ib = b.length() - 1;
        StringBuilder sb = new StringBuilder();

        while (ia >= 0 || ib >= 0 || carry > 0) {
            int da = ia >= 0 ? a.charAt(ia) - '0': 0;
            int db = ib >= 0 ? b.charAt(ib) - '0': 0;

            int res = da + db + carry;
            carry = 0;
            if (res > 1) {
                carry = 1;
                res %= 2;
            }

            sb.insert(0, res);
            ia--;
            ib--;
        }

        return sb.toString();
    }
}
