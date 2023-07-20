package com.disaster.infrastructure.oldversion.link;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKLists {
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

        Node l3 = new Node(2);
        Node nextNode5 = new Node(5);
        Node nextNode6 = new Node(6);
        l3.next = nextNode5;
        nextNode5.next = nextNode6;
        Node[] listNodes = new Node[3];
        listNodes[0] = l1;
        listNodes[1] = l2;
        listNodes[2] = l3;
        System.out.println(mergeKLists(listNodes));
    }

    /**
     * 优先队列 pq 中的元素个数最多是 k，所以一次 poll 或者 add 方法的时间复杂度是 O(logk)；
     * 所有的链表节点都会被加入和弹出 pq，所以算法整体的时间复杂度是 O(Nlogk)，其中 k 是链表的条数，N 是这些链表的节点总数
     *
     * @param nodes
     * @return
     */
    private static Node mergeKLists(Node[] nodes) {
        if (nodes.length == 0) {
            return null;
        }
        Node dummy = new Node(-1), p = dummy;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (Node head : nodes) {
            if (head != null) {
                pq.offer(head);
            }
        }
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            p.next = poll;
            if (poll.next != null) {
                pq.offer(poll.next);
            }
            p = p.next;
        }
        return dummy.next;
    }
}
