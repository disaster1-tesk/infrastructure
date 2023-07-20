package com.disaster.infrastructure.oldversion.link;

public class Node {
    int val;
    Node next;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node next = null;
        sb.append("[");
        sb.append(val);
        next = this.next;
        while (next != null) {
            sb.append(",");
            sb.append(next.val);
            next = next.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
