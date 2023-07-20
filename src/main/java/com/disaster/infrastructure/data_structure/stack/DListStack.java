package com.disaster.infrastructure.data_structure.stack;

/**
 * list implement an stack
 *
 * @param <V> the type parameter
 * @author disaster
 * @version 1.0
 */
public class DListStack<V> {
    private Node<V> node;
    private transient int size;

    /**
     * Instantiates a new D list stack.
     */
    public DListStack() {
        this.node = null;
    }


    /**
     * Push.
     *
     * @param val the val
     */
    public void push(V val) {
        Node<V> vNode = new Node<>(node, val);
        node = vNode;
        size++;
    }


    /**
     * Pop v.
     *
     * @return the v
     */
    public V pop() {
        V v = peek();
        node = node.next;
        size--;
        return v;
    }


    /**
     * Peek v.
     *
     * @return the v
     */
    public V peek() {
        if (size() == 0)
            throw new IllegalStateException("Stack is empty");
        return node.val;
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return "DListStack{" +
                "node=" + node +
                ", size=" + size +
                '}';
    }

    private class Node<V> {
        private Node<V> next;
        private V val;

        /**
         * Instantiates a new Node.
         *
         * @param next the next
         * @param val  the val
         */
        public Node(Node<V> next, V val) {
            this.next = next;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", val=" + val +
                    '}';
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DListStack<Object> dListStack = new DListStack<>();
        dListStack.push(1);
        dListStack.push(2);
        dListStack.push(3);
        dListStack.push(4);
        System.out.println("dListStack = " + dListStack);
        System.out.println(dListStack.peek());
        System.out.println(dListStack.pop());
        System.out.println("dListStack = " + dListStack);
    }
}
