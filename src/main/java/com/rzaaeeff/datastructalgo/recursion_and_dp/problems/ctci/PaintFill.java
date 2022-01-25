package com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci;

import static com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci.PaintFill.Color.B;
import static com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci.PaintFill.Color.G;
import static com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci.PaintFill.Color.R;

public class PaintFill {
    enum Color {
        R("\u001B[31m" + "■"),
        G("\u001B[32m" + "■"),
        B("\u001B[34m" + "■");

        private String value;

        Color(String value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return value;
        }
    }

    public static void main(String[] args) {
        Color[][] screen = new Color[][]{
                new Color[]{R, R, R, R, G, G, B, R, R, R},
                new Color[]{R, R, R, G, G, B, B, R, R, R},
                new Color[]{R, R, R, G, G, B, B, B, R, R},
                new Color[]{R, R, G, G, B, B, B, B, R, R},
                new Color[]{R, G, R, G, B, B, B, B, B, R},
                new Color[]{R, G, R, G, B, B, B, B, B, R},
                new Color[]{G, R, R, B, B, B, B, B, B, B},
                new Color[]{G, R, R, B, B, B, B, B, B, B}
        };

        printScreen(screen);
        paintFill(screen, 0, 0, B);
        printScreen(screen);
    }

    private static void printScreen(Color[][] screen) {
        System.out.println();
        for (Color[] row : screen) {
            for (Color cell : row) {
                System.out.print(cell);
                System.out.print(cell);
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void paintFill(Color[][] screen, int row, int col, Color newColor) {
        if (row < 0 || row >= screen.length || col < 0 || col >= screen[row].length) return;
        Color originalColor = screen[row][col];
        if (originalColor == newColor) return;
        paintFillHelper(screen, row, col, newColor, originalColor);
    }

    private static void paintFillHelper(Color[][] screen, int row, int col, Color newColor, Color originalColor) {
        if (row < 0 || row >= screen.length || col < 0 || col >= screen[row].length) return;
        if (screen[row][col] != originalColor) return;

        screen[row][col] = newColor;

        paintFillHelper(screen, row + 1, col, newColor, originalColor); // down
        paintFillHelper(screen, row - 1, col, newColor, originalColor); // up
        paintFillHelper(screen, row, col + 1, newColor, originalColor); // right
        paintFillHelper(screen, row, col - 1, newColor, originalColor); // left
    }
}
