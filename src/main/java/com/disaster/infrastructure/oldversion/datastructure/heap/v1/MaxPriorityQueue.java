package com.disaster.infrastructure.oldversion.datastructure.heap.v1;

/**
 * The type Max priority queue.
 *
 * @param <V> the type parameter
 */
public class MaxPriorityQueue<V extends Comparable<V>> {
    private V[] arr;
    private int capacity;
    private int size;

    /**
     * Instantiates a new Max priority queue.
     *
     * @param capacity the capacity
     */
    public MaxPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.arr = (V[]) new Comparable[capacity + 1];
        this.size = 0;
    }

    /**
     * Parent int.
     *
     * @param root the root
     * @return the int
     */
    public int parent(int root) {
        return root / 2;
    }

    /**
     * Left int.
     *
     * @param root the root
     * @return the int
     */
    public int left(int root) {
        return root * 2;
    }

    /**
     * Right int.
     *
     * @param root the root
     * @return the int
     */
    public int right(int root) {
        return root * 2 + 1;
    }

    /**
     * Has parent boolean.
     *
     * @param root the root
     * @return the boolean
     */
    public boolean hasParent(int root) {
        return Math.floor((double) (root - 1) / 2) >= 0;
    }

    /**
     * Has left boolean.
     *
     * @param root the root
     * @return the boolean
     */
    public boolean hasLeft(int root) {
        return root * 2 < size && arr[root * 2] != null;
    }

    /**
     * Has right boolean.
     *
     * @param root the root
     * @return the boolean
     */
    public boolean hasRight(int root) {
        return root * 2 + 1 < size && arr[root * 2 + 1] != null;
    }

    private void swap(int a, int b) {
        V tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private boolean less(int a, int b) {
        return arr[a].compareTo(arr[b]) < 0;
    }

    /**
     * Swim.
     *
     * @param x the x
     */
    public void swim(int x) {
        while (x > 1 && less(parent(x), x)) {
            swap(parent(x), x);
            x = parent(x);
        }
    }

    /**
     * Sink.
     *
     * @param x the x
     */
    public void sink(int x) {
        while (hasLeft(x)) {
            int max = left(x);
            if (hasRight(x) && less(max, right(x))) {
                max = right(x);
            }
            if (less(max, x)) {
                break;
            }
            swap(max, x);
            x = max;
        }
    }


    /**
     * Insert.
     *
     * @param value the value
     */
    public void offer(V value) {
        if (size >= capacity) {
            throw new IllegalArgumentException("heap is full");
        }
        size++;
        arr[size] = value;
        swim(size);
    }

    /**
     * Del top v.
     *
     * @return the v
     */
    public V take() {
        if (size == 0) {
            throw new IllegalArgumentException("heap is empty");
        }
        V top = arr[1];
        swap(1, size);
        arr[size] = null;
        size--;
        sink(1);
        return top;
    }

}
