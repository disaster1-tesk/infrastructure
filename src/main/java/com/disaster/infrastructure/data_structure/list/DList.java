package com.disaster.infrastructure.data_structure.list;

import java.util.*;

/**
 * list simple implementation
 *
 * @param <V> the type parameter
 * @author disaster
 * @version 1.0
 */
public class DList<V> implements List<V> {

    private transient int size;

    private transient Node<V> head;

    private transient Node<V> tail;

    /**
     * Instantiates a new D list.
     */
    public DList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }


    @Override
    public Iterator<V> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<V> x = head; x != null; x = x.next)
            result[i++] = x.val;
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    /**
     * Link last.
     *
     * @param v the v
     */
    void linkLast(V v) {
        final Node<V> l = tail;
        final Node<V> newNode = new Node<V>(l, v,null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;
    }

    /**
     * Link before.
     *
     * @param v    the v
     * @param succ the succ
     */
    void linkBefore(V v, Node<V> succ) {
        // assert succ != null;
        final Node<V> pred = succ.prev;
        final Node<V> newNode = new Node<V>(pred, v, succ);
        succ.prev = newNode;
        if (pred == null)
            head = newNode;
        else
            pred.next = newNode;
        size++;
    }

    @Override
    public boolean add(V v) {
        linkLast(v);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<V> x = head; x != null; x = x.next) {
                if (x.val == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<V> x = head; x != null; x = x.next) {
                if (o.equals(x.val)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Unlink v.
     *
     * @param x the x
     * @return the v
     */
    V unlink(Node<V> x) {
        // assert x != null;
        final V element = x.val;
        final Node<V> next = x.next;
        final Node<V> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.val = null;
        size--;
        return element;
    }

    private V unlinkFirst(Node<V> f) {
        // assert f == first && f != null;
        final V element = f.val;
        final Node<V> next = f.next;
        f.val = null;
        f.next = null; // help GC
        head = next;
        if (next == null)
            tail = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends V> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public V get(int index) {
        return node(index).val;
    }


    /**
     * Node node.
     *
     * @param index the index
     * @return the node
     */
    Node<V> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<V> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<V> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }


    @Override
    public V set(int index, V element) {
        return null;
    }

    @Override
    public void add(int index, V element) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

    @Override
    public V remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<V> node = head; node != null; node = node.next) {
                if (node.val == null)
                    return index;
                index++;
            }
        } else {
            for (Node<V> node = head; node != null; node = node.next) {
                if (node.val.equals(o))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<V> listIterator() {
        return null;
    }

    @Override
    public ListIterator<V> listIterator(int index) {
        return null;
    }

    @Override
    public List<V> subList(int fromIndex, int toIndex) {
        return null;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    @Override
    public String toString() {
        return "DList{" +
                "size=" + size +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }

    /**
     * Node
     *
     * @param <V>
     */
    private static class Node<V> {
        private Node<V> next;
        private Node<V> prev;
        private V val;

        /**
         * Instantiates a new Node.
         *
         * @param prev the prev
         * @param val  the val
         * @param next the next
         */
        public Node(Node<V> prev, V val, Node<V> next) {
            this.next = next;
            this.prev = prev;
            this.val = val;
        }

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DList<Integer> list = new DList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.get(1));
        list.remove(new Integer(2));
        System.out.println(list.get(1));
        System.out.println("list = " + list);
    }
}
