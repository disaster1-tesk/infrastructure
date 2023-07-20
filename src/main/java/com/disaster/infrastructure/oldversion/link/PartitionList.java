package com.disaster.infrastructure.oldversion.link;

public class PartitionList {
    public static void main(String[] args) {
        Node l1 = new Node(1);
        Node nextNode1 = new Node(4);
        Node nextNode2 = new Node(3);
        Node nextNode3 = new Node(2);
        Node nextNode4 = new Node(5);
        Node nextNode5 = new Node(2);
        l1.next = nextNode1;
        nextNode1.next = nextNode2;
        nextNode2.next = nextNode3;
        nextNode3.next = nextNode4;
        nextNode4.next = nextNode5;
        System.out.println(partitionList(l1, 3));
    }

    public static Node partitionList(Node node, int x) {
        Node dummy1 = new Node(-1), dummy2 = new Node(-1),
                p = node, p1 = dummy1, p2 = dummy2;
        while (p != null) {
            if (p.val >= x) {
                p2.next = p;
                p2 = p2.next;
            } else {
                p1.next = p;
                p1 = p1.next;
            }
            Node temp = p.next;
            p.next = null;
            p = temp;
        }
        p1.next = dummy2.next;
        return dummy1.next;
    }
}
