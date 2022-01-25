package com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci;

// Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
// The robot can only move in two directions, right and down, but certain cells are
// "off limits" such that the robot cannot step on them. Design an algorithm to find
// a path for the robot from the top left to the bottom right.

import com.rzaaeeff.datastructalgo.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class RobotInAGrid {
    public static void main(String[] args) {
        boolean[][] grid = new boolean[6][10];
        for (boolean[] row : grid) {
            Arrays.fill(row, true);
        }
        grid[0][7] = false;
        grid[1][2] = false;
        grid[2][0] = false;
        grid[2][5] = false;
        grid[3][6] = false;
        grid[4][2] = false;
        grid[5][6] = false;

//         uncomment either both of these two lines
//         grid[2][1] = false;
//         grid[0][3] = false;
//         or both of these two lines
//         grid[4][9] = false;
//         grid[5][8] = false;
//         to make the end unreachable for the robot

        System.out.println(getPath(grid));
    }

    private static boolean recursive(boolean[][] grid, int c, int r) {
        if (r >= grid.length || c >= grid[0].length) return false;
        else if (r == grid.length - 1 && c == grid[0].length - 1) return true;
        else if (!grid[r][c]) return false;

        return recursive(grid, c + 1, r) || recursive(grid, c, r + 1);
    }

    private static ArrayList<Pair<Integer, Integer>> getPath(boolean[][] maze) {
        if (maze == null || maze.length == 0) return null;

        ArrayList<Pair<Integer, Integer>> path = new ArrayList<>();
        HashSet<Pair<Integer, Integer>> failedPairs = new HashSet<>();
        if (getPath(maze, maze.length - 1, maze[0].length - 1, path, failedPairs)) {
            return path;
        }

        return null;
    }

    private static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Pair<Integer, Integer>> path,
                                   HashSet<Pair<Integer, Integer>> failedPairs) {
        if (col < 0 || row < 0 || !maze[row][col]) return false;

        Pair<Integer, Integer> p = new Pair<>(row, col);
        if (failedPairs.contains(p)) return false;

        boolean isAtOrigin = row == 0 && col == 0;

        if (isAtOrigin || getPath(maze, row - 1, col, path, failedPairs)
                || getPath(maze, row, col - 1, path, failedPairs)) {
            path.add(p);
            return true;
        }

        failedPairs.add(p);
        return false;
    }
}
