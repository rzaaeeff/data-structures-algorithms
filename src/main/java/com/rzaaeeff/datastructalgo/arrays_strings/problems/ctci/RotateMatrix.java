package com.rzaaeeff.datastructalgo.arrays_strings.problems.ctci;

import java.util.Arrays;

public class RotateMatrix {
    public static void main(String[] args) {
        Object[][] matrix = new Object[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        if (!rotateMatrix0(matrix)) throw new AssertionError();
        if (!Arrays.deepEquals(matrix, new Object[][] {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        })) throw new AssertionError();

        matrix = new Object[][] {};
        if (rotateMatrix0(matrix)) throw new AssertionError();

        matrix = new Object[][] {
                {1, 2, 3},
                {4, 5, 6}
        };
        if (rotateMatrix0(matrix)) throw new AssertionError();
    }

    private static boolean rotateMatrix0(Object[][] matrix) {
        if (matrix.length == 0 || matrix[0].length != matrix.length) return false;
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;

                // save top
                Object top = matrix[first][i];

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top;
            }
        }

        return true;
    }
}
