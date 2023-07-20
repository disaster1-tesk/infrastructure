package com.disaster.infrastructure.data_structure.stack;

import java.util.Arrays;

/**
 * array implement an array
 *
 * @param <V> the type parameter
 * @author disaster
 * @version 1.0
 */
public class DArrayStack<V> {
    private transient V[] array;
    private transient int size = 0;
    private int capacity;
    private int expandRatio = 2;

    /**
     * Instantiates a new D array stack.
     */
    public DArrayStack() {
        this.array = (V[]) new Object[10];
        this.capacity = 10;
    }

    /**
     * Instantiates a new D array stack.
     *
     * @param array the array
     */
    public DArrayStack(V[] array) {
        this.array = array;
        this.capacity = array.length;
    }


    /**
     * Push.
     *
     * @param v the v
     */
    public void push(V v) {
        checkSize();
        array[size] = v;
        size++;
    }

    /**
     * Pop v.
     *
     * @return the v
     */
    public V pop() {
        V val = array[size - 1];
        size--;
        return val;
    }

    /**
     * Peek v.
     *
     * @return the v
     */
    public V peek(){
        return array[size - 1];
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return size;
    }

    private void checkSize() {
        if (array.length == size) {
            expandSize();
        }
    }

    private void expandSize() {
        array = Arrays.copyOf(array, capacity * expandRatio);
        capacity = array.length;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DArrayStack<Object> dArrayStack = new DArrayStack<>();
        dArrayStack.push(1);
        dArrayStack.push(2);
        dArrayStack.push(3);
        dArrayStack.push(4);
        System.out.println("dArrayStack = " + dArrayStack);
        System.out.println(dArrayStack.peek());
        System.out.println(dArrayStack.pop());
        System.out.println("dArrayStack = " + dArrayStack);
    }

}
