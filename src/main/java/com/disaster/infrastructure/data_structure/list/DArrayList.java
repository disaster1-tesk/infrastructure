package com.disaster.infrastructure.data_structure.list;

import com.disaster.infrastructure.data_structure.array.DArray;

import java.util.Arrays;

/**
 * arraylist simple implementation
 *
 * @param <V> the type parameter
 * @author disaster
 * @version 1.0
 */
public class DArrayList<V> {
    private V[] nums;
    private int capacity;
    private int size = 0;
    private static int extendRatio = 2;

    /**
     * Instantiates a new D array list.
     */
    public DArrayList() {
        this.nums = (V[]) new Object[10];
        this.capacity = 10;
    }

    /**
     * Instantiates a new D array list.
     *
     * @param capacity the capacity
     */
    public DArrayList(int capacity) {
        this.capacity = capacity;
        this.nums = (V[]) new Object[size];
    }


    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return this.size;
    }

    /**
     * Capacity int.
     *
     * @return the int
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Set.
     *
     * @param index the index
     * @param v     the v
     */
    public void set(int index, V v) {
        checkNumSize(index);
        nums[index] = v;
    }


    /**
     * Add.
     *
     * @param v the v
     */
    public void add(V v) {
        if (size() == capacity()) extendCapacity();
        nums[size] = v;
        size++;
    }


    /**
     * Get v.
     *
     * @param index the index
     * @return the v
     */
    public V get(int index) {
        checkNumSize(index);
        return nums[index];
    }

    /**
     * Insert.
     *
     * @param index the index
     * @param v     the v
     */
    public void insert(int index, V v) {
        checkNumSize(index);
        if (size() == capacity()) extendCapacity();
        for (int i = size - 1; i >= index; i--) {
            nums[i] = nums[i - 1];
        }
        nums[index] = v;
        size++;
    }

    /**
     * Remove v.
     *
     * @param index the index
     * @return the v
     */
    public V remove(int index) {
        checkNumSize(index);
        V num = nums[index];
        for (int i = index; i < size - 1; i++) {
            nums[i] = nums[i + 1];
        }
        size--;
        return num;
    }


    /**
     * Extend capacity.
     */
    public void extendCapacity() {
        nums = Arrays.copyOf(nums, capacity * extendRatio);
        capacity = nums.length;
    }

    /**
     * Check num size.
     *
     * @param index the index
     */
    public void checkNumSize(int index) {
        if (index < 0 || index > this.capacity) {
            throw new IllegalArgumentException("Index out of range");
        }
    }

    @Override
    public String toString() {
        return "DArrayList{" +
                "nums=" + Arrays.toString(nums) +
                ", capacity=" + capacity +
                ", size=" + size +
                '}';
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DArrayList<Object> dList = new DArrayList<>();
        dList.add(1);
        dList.add(2);
        dList.insert(3, 2);
        System.out.println(dList.get(0));
        dList.remove(0);
        System.out.println(dList.get(3));
        System.out.println("dList = " + dList);
    }


}

