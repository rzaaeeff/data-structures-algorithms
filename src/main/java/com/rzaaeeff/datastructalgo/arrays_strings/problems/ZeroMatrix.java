package com.rzaaeeff.datastructalgo.arrays_strings.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ZeroMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };
        zeroMatrix2(matrix);
        if (!Arrays.deepEquals(matrix, new int[][]{
                {1, 0, 3},
                {0, 0, 0},
                {7, 0, 9}
        })) throw new AssertionError();

        matrix = new int[][]{
                {0, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        zeroMatrix2(matrix);
        if (!Arrays.deepEquals(matrix, new int[][]{
                {0, 0, 0},
                {0, 5, 0},
                {0, 0, 0}
        })) throw new AssertionError();
    }

    // time complexity = 2*m*n => m*n
    // space complexity = m+n
    private static void zeroMatrix0(int[][] matrix) {
        Set<String> zeros = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeros.add("c" + i);
                    zeros.add("r" + j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (zeros.contains("c" + i) || zeros.contains("r" + j))
                    matrix[i][j] = 0;
            }
        }
    }

    // time complexity = 2*m*n => m*n
    // space complexity = m+n
    private static void zeroMatrix1(int[][] matrix) {
        Set<Integer> columns = new HashSet<>();
        Set<Integer> rows = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    columns.add(i);
                    rows.add(j);
                }
            }
        }

        columns.forEach(i -> Arrays.fill(matrix[i], 0));
        columns.forEach(j -> {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][j] = 0;
            }
        });
    }

    // time complexity: 2*(m + n) + 2*m*n => m*n
    // space complexity: 1
    private static void zeroMatrix2(int[][] matrix) {
        boolean firstColHasZero = false;
        boolean firstRowHasZero = false;

        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstColHasZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstRowHasZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
