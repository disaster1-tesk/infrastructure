package com.disaster.infrastructure.data_structure.array;

import java.util.Arrays;

/**
 * array simple implementation
 *
 * @param <V> the type parameter
 * @author disaster
 * @version 1.0
 */
public class DArray<V> {
    private V[] elements;

    /**
     * Instantiates a new D array.
     *
     * @param capacity the capacity
     */
    public DArray(int capacity) {
        this.elements = (V[]) new Object[capacity];
    }



    /**
     * add elements to the array
     *
     * @param index
     * @param element
     */
    private void add(int index, V element) {
        for (int i = elements.length - 1; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
    }

    /**
     * remove elements from the array
     *
     * @param index
     */
    private void del(int index) {
        for (int i = elements.length - 1; i > index; i--) {
            elements[i] = elements[i + 1];
        }
    }

    /**
     * get the number of elements in the array
     *
     * @param index
     * @return
     */
    private V get(int index) {
        return elements[index];
    }

    /**
     * update the array with element
     *
     * @param index
     * @param element
     */
    private void update(int index, V element){
        elements[index] = element;
    }

    @Override
    public String toString() {
        return "DArray{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

    public static void main(String[] args) {
        DArray<Integer> dArray = new DArray<>(3);
        dArray.add(0,1);
        dArray.add(1,2);
        dArray.add(2,3);
        dArray.add(0,2);
        System.out.println("dArray = " + dArray);
    }



}
