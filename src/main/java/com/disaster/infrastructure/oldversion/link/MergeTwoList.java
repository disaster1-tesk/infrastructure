package com.disaster.infrastructure.oldversion.link;

public class MergeTwoList {
    public static void main(String[] args) {
        Node l1 = new Node(1);
        Node nextNode1 = new Node(2);
        Node nextNode2 = new Node(3);
        l1.next = nextNode1;
        nextNode1.next = nextNode2;

        Node l2 = new Node(1);
        Node nextNode3 = new Node(3);
        Node nextNode4 = new Node(4);
        l2.next = nextNode3;
        nextNode3.next = nextNode4;
        System.out.println(mergeTwoList(l1, l2));
    }

    public static Node mergeTwoList(Node node1, Node node2) {
        Node dummy = new Node(-1), p = dummy;
        Node p1 = node1, p2 = node2;
        while (p1 != null && p2 != null) {
            if (p1.val >= p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;
    }
}
