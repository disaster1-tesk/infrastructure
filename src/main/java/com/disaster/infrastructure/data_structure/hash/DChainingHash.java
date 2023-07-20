package com.disaster.infrastructure.data_structure.hash;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * quote Separate Chaining to figure out hash collisions
 *
 * @param <K>
 * @param <V>
 * @author disaster
 * @version 1.0
 */
public class DChainingHash<K, V> {
    private int size;
    private float threshold = 0.75f;
    private int capacity;
    private int extendRatio = 2;
    private List<List<Pair<K, V>>> buckets;

    public DChainingHash() {
        this.size = 0;
        this.capacity = 4;
        this.buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }


    public int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }


    public double loadFactory() {
        return (double) size / capacity;
    }

    public void put(K key, V value) {
        if (loadFactory() > threshold) {
            expend();
        }
        int hash = hash(key);
        List<Pair<K, V>> pairs = buckets.get(hash);
        for (Pair<K, V> pair : pairs) {
            if (pair.key == key) {
                pair.value = value;
                return;
            }
        }
        pairs.add(new Pair<>(key, value));
        size++;
    }


    public V get(K key) {
        int hash = hash(key);
        List<Pair<K, V>> pairs = buckets.get(hash);
        for (Pair<K, V> pair : pairs) {
            if (pair.key.equals(key))
                return pair.value;
        }
        return null;
    }


    public void remove(K key) {
        int hash = hash(key);
        List<Pair<K, V>> pairs = buckets.get(hash);
        if (pairs.isEmpty()) return;
        for (Pair<K, V> pair : pairs) {
            if (pair.key.equals(key))
                pairs.remove(pair);
        }
        size--;
    }

    void print() {
        for (List<Pair<K, V>> bucket : buckets) {
            List<String> res = new ArrayList<>();
            for (Pair pair : bucket) {
                res.add(pair.key + " -> " + pair.value);
            }
            System.out.println(res);
        }
    }

    private void expend() {
        List<List<Pair<K, V>>> temp = buckets;
        capacity *= extendRatio;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;
        for (List<Pair<K, V>> pairs : temp) {
            for (Pair<K, V> pair : pairs) {
                if (pair != null)
                    put(pair.key, pair.value);
            }
        }
    }


    private class Pair<K, V> {
        private K key;
        private V value;

        /**
         * Instantiates a new Pair.
         *
         * @param key   the key
         * @param value the value
         */
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        DChainingHash<String, Object> dChainingHash = new DChainingHash<>();
        dChainingHash.put("disaster", "disaster");
        dChainingHash.put("disaster1", "disaster1");
        dChainingHash.put("disaster2", "disaster2");
        dChainingHash.put("disaster3", "disaster3");
        dChainingHash.put("disaster4", "disaster4");
        dChainingHash.print();
    }
}
