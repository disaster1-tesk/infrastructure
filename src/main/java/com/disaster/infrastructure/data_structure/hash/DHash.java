package com.disaster.infrastructure.data_structure.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * hash simple implementation,hasn't considered hash collisions
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 * @author disaster
 * @version 1.0
 */
public class DHash<K, V> {

    private List<Pair<K, V>> buckets;
    private int capacity;
    private float loadFactor;

    /**
     * Instantiates a new D hash.
     */
    public DHash() {
        buckets = new ArrayList<Pair<K, V>>();
        this.capacity = 100;
        for (int i = 0; i < capacity; i++) {
            buckets.add(null);
        }
    }

    /**
     * Hash int.
     *
     * @param key the key
     * @return the int
     */
    public int hash(K key) {
        int i = Math.abs(key.hashCode());
        return i % capacity;
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    public void put(K key, V value) {
        int hash = hash(key);
        Pair<K, V> kvPair = buckets.get(hash);
        if (Objects.nonNull(kvPair)) return;
        buckets.add(hash, new Pair<>(key, value));
    }

    /**
     * Get v.
     *
     * @param key the key
     * @return the v
     */
    public V get(K key) {
        int hash = hash(key);
        Pair<K, V> kvPair = buckets.get(hash);
        if (Objects.nonNull(kvPair)) return kvPair.value;
        return null;
    }


    /**
     * Remove.
     *
     * @param key the key
     */
    public void remove(K key) {
        int hash = hash(key);
        buckets.set(hash, null);
    }


    /**
     * Pair set list.
     *
     * @return the list
     */
    public List<Pair<K, V>> pairSet() {
        ArrayList<Pair<K, V>> pairs = new ArrayList<>();
        for (Pair<K, V> bucket : buckets) {
            if (bucket != null)
                pairs.add(bucket);
        }
        return pairs;
    }

    /**
     * Key set list.
     *
     * @return the list
     */
    public List<K> keySet() {
        ArrayList<K> pairs = new ArrayList<>();
        for (Pair<K, V> bucket : buckets) {
            if (bucket != null)
                pairs.add(bucket.key);
        }
        return pairs;
    }


    /**
     * Value set list.
     *
     * @return the list
     */
    public List<V> valueSet() {
        ArrayList<V> pairs = new ArrayList<>();
        for (Pair<K, V> bucket : buckets) {
            if (bucket != null)
                pairs.add(bucket.value);
        }
        return pairs;
    }


    /**
     * Print.
     */
    public void print() {
        for (Pair kv : pairSet()) {
            System.out.println(kv.key + " -> " + kv.value);
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
        DHash<String, Object> dHash = new DHash<>();
        dHash.put("disaster", "hello");
        dHash.put("disaster1", "world");
        dHash.print();
        System.out.println(dHash.get("disaster"));
        System.out.println(dHash.keySet());
        System.out.println(dHash.valueSet());
    }
}
