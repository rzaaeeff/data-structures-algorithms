package com.rzaaeeff.datastructalgo.arrays_strings.problems;

public class StringRotation {
    public static void main(String[] args) {
        if (!isRotation("waterbottle", "erbottlewat")) throw new AssertionError();
        if (!isRotation("abcdef", "fabcde")) throw new AssertionError();
        if (isRotation("", "abc")) throw new AssertionError();
        if (isRotation("abcd", "cab")) throw new AssertionError();
    }

    private static boolean isRotation(String original, String rotation) {
        if (original.length() == 0 || original.length() != rotation.length())
            return false;

        return (rotation + rotation).contains(original);
    }
}
