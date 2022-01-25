package com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci;

import java.io.IOException;

public class TowerOfHanoi {
    public static void main(String[] args) throws IOException {
        towerOfHanoi(6, 1, 2, 3);
    }

    private static int count = 1;
    private static Process process;

    private static void towerOfHanoi(int n, int a, int b, int c) throws IOException {
        if (n > 0) {
            towerOfHanoi(n - 1, a, c, b);
            System.in.read();
            if (process != null) process.destroyForcibly();
            String text = String.format("%d: Move a disc from %d to %d.", count++, a, c);
            System.out.println(text);
            process = new ProcessBuilder("/usr/bin/say", "\"" + text + "\"").start();
            towerOfHanoi(n - 1, b, a, c);
        }
    }
}
