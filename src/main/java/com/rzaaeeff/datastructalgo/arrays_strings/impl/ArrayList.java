package com.rzaaeeff.datastructalgo.arrays_strings.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class ArrayList<T> {
    private Object[] array;
    private int size;

    private static final int DEFAULT_SIZE = 10;

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    public ArrayList(int size) {
        if (size < DEFAULT_SIZE) size = DEFAULT_SIZE;

        array = new Object[size];
    }

    Object[] getArray() {
        return array;
    }

    void ensureCapacity(int index) {
        if (index >= array.length) {
            if (array.length == Integer.MAX_VALUE) throw new OutOfMemoryError();

            int length = array.length;
            length <<= 1;

            if (length < 0) length = Integer.MAX_VALUE;

            Object[] newArray = new Object[length];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    public void trimToSize() {
        if (size < array.length) {
            int newSize = Math.max(size, DEFAULT_SIZE);
            Object[] newArray = new Object[newSize];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
            size = newSize;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], o))
                return true;
        }

        return false;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    public void add(T t) {
        ensureCapacity(size + 1);
        array[size++] = t;
    }

    public boolean remove(Object o) {
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, array[i])) {
                System.arraycopy(array, i + 1, array, i, size - i);
                size--;
                count++;
            }
        }

        return count > 0;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object val : c) {
            if (!contains(val)) {
                return false;
            }
        }

        return true;
    }

    public void addAll(Collection<? extends T> c) {
        for (T val : c) {
            add(val);
        }
    }

    public void addAll(int index, Collection<? extends T> c) {
        for (T val : c) {
            add(index++, val);
        }
    }

    public void removeAll(Collection<?> c) {
        for (Object val : c) {
            remove(val);
        }
    }

    public void clear() {
        Arrays.fill(array, null);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayList<?> arrayList = (ArrayList<?>) o;

        if (size != arrayList.size) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(array, arrayList.array);
    }

    public int hashCode() {
        int result = Arrays.hashCode(array);
        result = 31 * result + size;
        return result;
    }

    void rangeCheck(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
    }

    public T get(int index) {
        rangeCheck(index);
        return (T) array[index];
    }

    public T set(int index, T element) {
        rangeCheck(index);
        T oldVal = (T) array[index];
        array[index] = element;
        return oldVal;
    }

    public void add(int index, T element) {
        rangeCheck(index);
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    public T remove(int index) {
        rangeCheck(index);
        T val = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return val;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, array[i])) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, array[i])) {
                return i;
            }
        }

        return -1;
    }
}
