package com.rzaaeeff.datastructalgo.arrays_strings.impl;

import com.rzaaeeff.datastructalgo.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class HashTable<K, V> {
    static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            if (key == null) throw new NullPointerException("Key cannot be null");

            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<Entry<K, V>>[] table;
    private int size;

    public HashTable() {
        this(100);
    }

    public HashTable(int size) {
        this.size = 0;
        this.table = (LinkedList<Entry<K, V>>[]) new LinkedList[size];

        for (int i = 0; i < 100; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    public boolean containsValue(Object value) {
        for (LinkedList<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                if (entry.value.equals(value)) return true;
            }
        }

        return false;
    }

    public V get(Object key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) throw new NullPointerException("Entry with key " + key  + " could not be found");

        return entry.value;
    }

    private Entry<K, V> getEntry(Object key) {
        Pair<Integer, Integer> pair = getIndexes(key);
        if (pair == null) return null;
        return table[pair.fst].get(pair.snd);
    }

    private Pair<Integer, Integer> getIndexes(Object key) {
        int index = index(key);

        if (table[index].size() > 0) {
            LinkedList<Entry<K, V>> list = table[index(key)];

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key.equals(key)) return Pair.of(index, i);
            }
        }

        return null;
    }

    private int index(Object key) {
        return (System.identityHashCode(key) & 0x7FFFFFFF) % table.length;
    }

    public V put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        int index = index(key);
        table[index].add(entry);
        size++;

        return entry.value;
    }

    public V remove(Object key) {
        Pair<Integer, Integer> indexes = getIndexes(key);
        if (indexes == null) throw new NullPointerException("Entry with a key " + key + " could not be found");
        Entry<K, V> entry = table[indexes.fst].get(indexes.snd);
        table[indexes.fst].remove(entry);
        size--;

        return entry.value;
    }

    public void putAll(HashTable<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.key, entry.value);
        }
    }

    public void clear() {
        for (LinkedList list : table) {
            list.clear();
        }
        size = 0;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (LinkedList<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                keySet.add(entry.key);
            }
        }

        return keySet;
    }

    public Set<V> values() {
        Set<V> valueSet = new HashSet<>();
        for (LinkedList<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                valueSet.add(entry.value);
            }
        }

        return valueSet;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        for (LinkedList<Entry<K, V>> list : table) {
            entrySet.addAll(list);
        }

        return entrySet;
    }

    public V getOrDefault(Object key, V defaultValue) {
        Entry<K, V> entry = getEntry(key);

        if (entry == null || entry.value == null) return defaultValue;
        return entry.value;
    }
}