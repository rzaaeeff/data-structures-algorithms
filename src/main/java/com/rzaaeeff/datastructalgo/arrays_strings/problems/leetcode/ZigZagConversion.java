package com.rzaaeeff.datastructalgo.arrays_strings.problems.leetcode;

// link: https://leetcode.com/problems/zigzag-conversion

public class ZigZagConversion {
    public static void main(String[] args) {
        if (!convert1("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR")) throw new AssertionError();
        if (!convert1("PAYPALISHIRING", 4).equals("PINALSIGYAHRPI")) throw new AssertionError();
    }

    private static String convert0(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        Character[][] sMatrix = new Character[numRows][s.length()];
        StringBuilder sb = new StringBuilder();
        int column = 0;
        int row = 0;
        int counter = 0;

        for (int i = 0; i < s.length(); i++) {
            if (counter > 0 && counter < numRows - 1) {
                sMatrix[numRows - counter - 1][column] = s.charAt(i);
            } else {
                row = 0;
                int j = i;

                for (; j < Math.min(i + numRows, s.length()); j++) {
                    sMatrix[row++][column] = s.charAt(j);
                }

                i = j - 1;
                counter = 0;
            }

            column++;
            counter++;
        }

        for (row = 0; row < numRows; row++) {
            for (column = 0; column < sMatrix[row].length; column++) {
                Character ch = sMatrix[row][column];
                if (ch != null) sb.append(ch);
            }
        }

        return sb.toString();
    }

    private static String convert1(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < rows.length; i++) rows[i] = new StringBuilder();

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[curRow].append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder(s.length());
        for (StringBuilder row : rows) ret.append(row);

        return ret.toString();
    }
}
