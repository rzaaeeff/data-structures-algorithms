package com.rzaaeeff.datastructalgo.arrays_strings.impl;

public class StringBuilder {
    private static final int DEFAULT_SIZE = 16;
    private static final int MAX_SIZE = Integer.MAX_VALUE - 8;
    private int size = 0;
    private char[] array;

    public StringBuilder() {
        this(DEFAULT_SIZE);
    }

    public StringBuilder(String initialString) {
        this(initialString.length());
        array = initialString.toCharArray();
        this.size = initialString.length();
    }

    public StringBuilder(int initialSize) {
        if (initialSize > MAX_SIZE) throw new OutOfMemoryError();
        this.array = new char[Math.max(DEFAULT_SIZE, initialSize)];
    }

    private void ensureCapacity(int length) {
        if (length <= array.length - size)
            return;

        if (array.length == MAX_SIZE) throw new OutOfMemoryError();

        int newCapacity = array.length << 1;
        if (newCapacity < 0) newCapacity = MAX_SIZE;

        char[] newArray = new char[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        ensureCapacity(length);
    }

    public void trimToSize() {
        if (array.length <= size || array.length <= DEFAULT_SIZE)
            return;

        char[] newArray = new char[size];
        System.arraycopy(array, 0, array, 0, size);
        array = newArray;
    }

    public void append(char c) {
        ensureCapacity(1);
        array[size++] = c;
    }

    public void append(char[] chars) {
        ensureCapacity(chars.length);
        for (char aChar : chars) {
            array[size++] = aChar;
        }
    }

    public void append(String s) {
        append(s.toCharArray());
    }

    public void delete(int pos) {
        System.arraycopy(array, pos + 1, array, pos, size - pos);
        size--;
    }

    private void rangeCheck(int start, int end) {
        if (start < 0 || start >= size || end < 1 ||
                end > size || start >= end) throw new IllegalArgumentException();
    }


    public void delete(int start, int end) {
        rangeCheck(start, end);
        System.arraycopy(array, end - 1, array, start, size - (start - end));
        size -= (start - end);
    }

    public void replace(char[] chars, int start) {
        replace(chars, start, start + chars.length);
    }

    public void replace(char[] chars, int start, int end) {
        rangeCheck(start, end);
        if (chars.length > (start - end)) throw new StringIndexOutOfBoundsException();
        for (int i = start; i < end; i++) {
            array[start] = chars[i - start];
        }
    }

    public void replace(String s, int start) {
        replace(s.toCharArray(), start);
    }

    public void replace(String s, int start, int end) {
        replace(s.toCharArray(), start, end);
    }

    public void replace(char c, int pos) {
        replace(new char[]{c}, pos, pos + 1);
    }

    public String toString() {
        return new String(array, 0, size);
    }
}
