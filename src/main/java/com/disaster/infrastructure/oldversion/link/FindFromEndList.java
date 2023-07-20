package com.disaster.infrastructure.oldversion.link;

public class FindFromEndList {
    public static void main(String[] args) {
        Node l1 = new Node(1);
        Node nextNode1 = new Node(2);
        Node nextNode2 = new Node(3);
        l1.next = nextNode1;
        nextNode1.next = nextNode2;
        System.out.println(removeNthFromEnd(l1, 1));
    }

    public static Node findFromEndList(Node head, int index) {
        Node p1 = head, p2 = head;
        for (int i = 0; i < index; i++) {
            p1 = p1.next;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public static Node removeNthFromEnd(Node head, int index) {
        Node dummy = new Node(-1);
        dummy.next = head;
        Node fromEndList = findFromEndList(dummy, index + 1);
        fromEndList.next = fromEndList.next.next;
        return dummy.next;
    }
}
