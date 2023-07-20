package com.disaster.infrastructure.data_structure.queue;

import java.util.Arrays;

/**
 * array implement a queue，array is a cycle
 *
 * @param <V> the type parameter
 * @author disaster
 * @version 1.0
 */
public class DArrayQueue<V> {
    private V[] nums;
    private int front;
    private int size;

    /**
     * Instantiates a new D array queue.
     *
     * @param capacity the capacity
     */
    public DArrayQueue(int capacity) {
        nums = (V[]) new Object[capacity];
        front = size = 0;
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return size;
    }

    /**
     * Capacity int.
     *
     * @return the int
     */
    public int capacity() {
        return nums.length;
    }

    /**
     * Push.
     *
     * @param v the v
     */
    public void push(V v) {
        if (size == capacity()) throw new IndexOutOfBoundsException("size is out of bounds");
        int rear = (front + size) % capacity();
        nums[rear] = v;
        size++;
    }

    /**
     * Pop v.
     *
     * @return the v
     */
    public V pop() {
        V peek = peek();
        front = (front + 1) % capacity();
        size--;
        return peek;
    }


    /**
     * Peek v.
     *
     * @return the v
     */
    public V peek() {
        return nums[front];
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return "DArrayQueue{" +
                "nums=" + Arrays.toString(nums) +
                ", front=" + front +
                ", size=" + size +
                '}';
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DArrayQueue<Object> dArrayQueue = new DArrayQueue<>(3);
        dArrayQueue.push(1);
        dArrayQueue.push(2);
        dArrayQueue.push(3);
        System.out.println("dArrayQueue = " + dArrayQueue);
        System.out.println(dArrayQueue.peek());
        System.out.println("dArrayQueue = " + dArrayQueue);
        System.out.println(dArrayQueue.pop());
        System.out.println("dArrayQueue = " + dArrayQueue);
        dArrayQueue.push(4);
        System.out.println("dArrayQueue = " + dArrayQueue);
    }
}
