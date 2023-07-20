package com.disaster.infrastructure.oldversion.example.lru;

import java.util.HashMap;

/**
 * least recently used Cache implementation
 *
 * @author wangwei
 * @version 1.0
 */
public class LRUCache<K, V> {

    // key -> Node(key, val)
    private HashMap<K, Node<K, V>> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private BidirectionalNode<K, V> cache;
    // 最大容量
    private int cap;

    @Override
    public String toString() {
        return "LRUCache{" +
                "map=" + map +
                ", cache=" + cache +
                ", cap=" + cap +
                '}';
    }

    public static void main(String[] args) {
        LRUCache<String, Object> lruCache = new LRUCache<>(3);
        for (int i = 0; i < 3; i++) {
            lruCache.put("key" + i, "value" + i);
        }
        lruCache.get("key" + 0);
        lruCache.get("key" + 2);
        System.out.println("lruCache = " + lruCache);
        lruCache.put("key" + 5,"value" + 5);
        System.out.println("lruCache = " + lruCache);
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        // 将该数据提升为最近使用的
        makeRecently(key);
        return map.get(key).getValue();
    }

    public void put(K key, V val) {
        if (map.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key, val);
            return;
        }
        if (cap == cache.size()) {
            // 删除最久未使用的元素
            removeLeastRecently();
        }
        // 添加为最近使用的元素
        addRecently(key, val);
    }


    /* 将某个 key 提升为最近使用的 */
    private void makeRecently(K key) {
        Node<K, V> x = map.get(key);
        // 先从链表中删除这个节点
        cache.remove(x);
        // 重新插到队尾
        cache.addLast(x);
    }


    /* 添加最近使用的元素 */
    private void addRecently(K key, V val) {
        Node<K, V> x = new Node<K, V>(key, val);
        // 链表尾部就是最近使用的元素
        cache.addLast(x);
        // 别忘了在 map 中添加 key 的映射
        map.put(key, x);
    }

    /* 删除某一个 key */
    private void deleteKey(K key) {
        Node<K, V> x = map.get(key);
        // 从链表中删除
        cache.remove(x);
        // 从 map 中删除
        map.remove(key);
    }

    /* 删除最久未使用的元素 */
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        Node<K, V> deletedNode = cache.removeFirst();
        // 同时别忘了从 map 中删除它的 key
        assert deletedNode != null;
        K deletedKey = deletedNode.key;
        map.remove(deletedKey);
    }

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new BidirectionalNode<K, V>();
    }


    private static class BidirectionalNode<K, V> {
        private Node<K, V> head, tail;
        private int size;

        public BidirectionalNode() {
            this.head = new Node<K, V>((K) new Object(), (V) new Object());
            this.tail = new Node<K, V>((K) new Object(), (V) new Object());
            head.setNext(tail);
            tail.setPrev(head);
            this.size = 0;
        }

        @Override
        public String toString() {
            return "BidirectionalNode{" +
                    "head=" + head +
                    ", tail=" + tail +
                    ", size=" + size +
                    '}';
        }

        public void addLast(Node<K, V> x) {
            x.setPrev(tail.getPrev());
            x.setNext(tail);
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        // 删除链表中的 x 节点（x 一定存在）
        // 由于是双链表且给的是目标 Node 节点，时间 O(1)
        public void remove(Node<K, V> x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        // 删除链表中第一个节点，并返回该节点，时间 O(1)
        public Node<K, V> removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node<K, V> first = head.next;
            remove(first);
            return first;
        }

        // 返回链表长度，时间 O(1)
        public int size() {
            return size;
        }


    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
        private Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public Node<K, V> getPrev() {
            return prev;
        }

        public void setPrev(Node<K, V> prev) {
            this.prev = prev;
        }
    }
}
