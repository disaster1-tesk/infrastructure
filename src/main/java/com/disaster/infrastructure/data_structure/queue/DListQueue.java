package com.disaster.infrastructure.data_structure.queue;

/**
 * list implement queue
 *
 * @param <V> the type parameter
 * @author disaster
 * @version 1.0
 */
public class DListQueue<V> {

    private Node<V> front;
    private Node<V> rear;
    private int size;


    /**
     * Push.
     *
     * @param v the v
     */
    public void push(V v) {
        Node<V> vNode = new Node<>(v, null);
        if (front == null) {
            front = vNode;
            rear = vNode;
        } else {
            rear.next = vNode;
            rear = vNode;
        }
        size++;
    }

    /**
     * Peek v.
     *
     * @return the v
     */
    public V peek() {
        if (size() == 0)
            throw new IndexOutOfBoundsException("size exclusive");
        return front.val;
    }

    /**
     * Pop v.
     *
     * @return the v
     */
    public V pop() {
        V peek = peek();
        front = front.next;
        size--;
        return peek;
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
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return "DListQueue{" +
                "front=" + front +
                ", rear=" + rear +
                ", size=" + size +
                '}';
    }

    private class Node<V> {
        private V val;
        private Node<V> next;

        /**
         * Instantiates a new Node.
         *
         * @param val  the val
         * @param next the next
         */
        public Node(V val, Node<V> next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DListQueue<Object> dListQueue = new DListQueue<>();
        dListQueue.push(1);
        dListQueue.push(2);
        dListQueue.push(3);
        System.out.println("dListQueue = " + dListQueue);
        System.out.println(dListQueue.peek());
        System.out.println("dListQueue = " + dListQueue);
        System.out.println(dListQueue.pop());
        System.out.println("dListQueue = " + dListQueue);
    }
}
