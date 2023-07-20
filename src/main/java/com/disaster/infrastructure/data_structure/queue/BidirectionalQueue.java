package com.disaster.infrastructure.data_structure.queue;

/**
 * list implement bidirectional queue
 *
 * @param <E> the type parameter
 * @author disaster
 * @version 1.0
 */
public class BidirectionalQueue<E> {
    private Node<E> front, rear;
    private int size;

    /**
     * Instantiates a new Bidirectional queue.
     */
    public BidirectionalQueue() {
        this.size = 0;
        front = rear = null;
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
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return size() == 0;
    }


    /**
     * Push first.
     *
     * @param e the e
     */
    public void pushFirst(E e) {
        push(e,true);
    }

    /**
     * Push last.
     *
     * @param e the e
     */
    public void pushLast(E e) {
        push(e,false);
    }


    /**
     * Pop first e.
     *
     * @return the e
     */
    public E popFirst() {
        return pop(true);
    }

    /**
     * Pop last e.
     *
     * @return the e
     */
    public E popLast() {
        return pop(false);
    }


    /**
     * Peek first e.
     *
     * @return the e
     */
    public E peekFirst() {
        return isEmpty() ? null: front.val;
    }


    /**
     * Peek last e.
     *
     * @return the e
     */
    public E peekLast() {
        return isEmpty() ? null: rear.val;
    }

    /**
     * Push.
     *
     * @param e       the e
     * @param isFront the is front
     */
    public void push(E e, boolean isFront) {
        Node<E> eNode = new Node<>(e, null, null);
        if (isEmpty()) {
            front = rear = eNode;
        } else if (isFront) {
            front.prev = eNode;
            eNode.next = front;
            front = eNode;
        } else {
            rear.next = eNode;
            eNode.prev = rear;
            rear = eNode;
        }
        size++;
    }


    /**
     * Pop e.
     *
     * @param isFront the is front
     * @return the e
     */
    public E pop(boolean isFront) {
        if (isEmpty()) {
            return null;
        }
        E v;
        if (isFront) {
            v = front.val;
            Node<E> eNode = front.next;
            if (eNode != null) {
                eNode.prev = null;
                front.next = null;
            }
            front = eNode;
        } else {
            v = rear.val;
            Node<E> eNode = rear.prev;
            if (eNode != null) {
                eNode.next = null;
                rear.prev = null;
            }
            rear = eNode;
        }
        size--;
        return v;
    }



    private class Node<E> {
        private E val;
        private Node<E> prev;
        private Node<E> next;

        /**
         * Instantiates a new Node.
         *
         * @param val  the val
         * @param prev the prev
         * @param next the next
         */
        public Node(E val, Node<E> prev, Node<E> next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }
}
