package com.disaster.infrastructure.data_structure.hash;


/**
 * quote open address to work out hash collision
 *
 * @param <K>
 * @param <V>
 * @author disaster
 * @version 1.0
 */
public class DOpenAddressHash<K, V> {
    private int size;
    private int capacity;
    private int extendRatio = 2;
    private float threshold = 0.75f;
    private Pair<K, V>[] buckets;
    private Pair removed;

    public DOpenAddressHash() {
        this.size = 0;
        this.capacity = 4;
        this.buckets = new Pair[capacity];
        this.removed = new Pair<>(-1, -1);
    }


    public int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public double loadFactory() {
        return (double) size / capacity;
    }

    public void put(K key, V val) {
        if (loadFactory() >= threshold) {
            expend();
        }
        int hash = hash(key);
        for (int i = 0; i < capacity; i++) {
            int j = (hash + i) % capacity;
            if (buckets[j] == null || buckets[j].equals(removed)) {
                buckets[j] = new Pair<>(key, val);
                size++;
                return;
            }
            if (buckets[j] != null) {
                buckets[j].value = val;
                return;
            }
        }
    }


    public V get(K key) {
        int hash = hash(key);
        for (int i = 0; i < capacity; i++) {
            int j = (hash + i) % capacity;
            if (buckets[j] == null)
                return null;
            if (buckets[j].key == key && buckets[j] != removed)
                return buckets[j].value;
        }
        return null;
    }


    public void remove(K key) {
        int hash = hash(key);
        for (int i = 0; i < capacity; i++) {
            int j = (hash + i) % capacity;
            if (buckets[j] == null) return;
            if (buckets[j] != removed && buckets[j].key == key) {
                buckets[j] = removed;
                size--;
                return;
            }
        }
    }


    public void expend() {
        Pair<K, V>[] temp = buckets;
        capacity *= extendRatio;
        this.buckets = new Pair[capacity];
        size = 0;
        for (Pair<K, V> pair : temp) {
            if (pair != null && pair != removed) {
                put(pair.key, pair.value);
            }
        }
    }

    public void print() {
        for (Pair pair : buckets) {
            if (pair != null) {
                System.out.println(pair.key + " -> " + pair.value);
            } else {
                System.out.println("null");
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
}
