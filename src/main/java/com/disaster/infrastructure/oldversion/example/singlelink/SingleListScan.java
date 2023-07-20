package com.disaster.infrastructure.oldversion.example.singlelink;


import java.util.Comparator;
import java.util.PriorityQueue;

public class SingleListScan {
    public static void main(String[] args) {
        mergeTwoList();
        mergeKList();
        downCountK();
        countMiddle();
        hasCycle();
        detectCycle();
        getIntersectionNode();
    }


    private static void getIntersectionNode(){
        ListNode l1 = new ListNode(1);
        ListNode nextNode1 = new ListNode(4);
        ListNode nextNode2 = new ListNode(5);
        l1.next = nextNode1;

        nextNode1.next = nextNode2;

        ListNode l2 = new ListNode(2);
        ListNode nextNode3 = new ListNode(3);
        l2.next = nextNode3;
        nextNode3.next = nextNode1;
    }


    private static void hasCycle() {
        ListNode l1 = new ListNode(1);
        ListNode nextNode1 = new ListNode(2);
        ListNode nextNode2 = new ListNode(3);
        ListNode nextNode3 = new ListNode(4);
        ListNode nextNode4 = new ListNode(5);
        l1.next = nextNode1;
        nextNode1.next = nextNode2;
        nextNode2.next = nextNode3;
        nextNode3.next = nextNode4;
//        nextNode4.next = l1;
        System.out.println(hasCycle(l1));
    }

    private static void detectCycle() {
        ListNode l1 = new ListNode(1);
        ListNode nextNode1 = new ListNode(2);
        ListNode nextNode2 = new ListNode(3);
        ListNode nextNode3 = new ListNode(4);
        ListNode nextNode4 = new ListNode(5);
        ListNode nextNode5 = new ListNode(6);
        l1.next = nextNode1;
        nextNode1.next = nextNode2;
        nextNode2.next = nextNode3;
        nextNode3.next = nextNode4;
        nextNode4.next = nextNode5;
        nextNode5.next = nextNode3;
        System.out.println(detectCycle(l1));
    }


    private static void countMiddle() {
        ListNode l1 = new ListNode(1);
        ListNode nextNode1 = new ListNode(2);
        ListNode nextNode2 = new ListNode(3);
        ListNode nextNode3 = new ListNode(4);
        ListNode nextNode4 = new ListNode(5);
        l1.next = nextNode1;
        nextNode1.next = nextNode2;
        nextNode2.next = nextNode3;
//        nextNode3.next = nextNode4;
        System.out.println(countMiddle(l1));
//        System.out.println(countMiddle1(l1));
    }

    private static void downCountK() {
        ListNode l1 = new ListNode(1);
        ListNode nextNode1 = new ListNode(2);
        ListNode nextNode2 = new ListNode(3);
        l1.next = nextNode1;
        nextNode1.next = nextNode2;
//        System.out.println(downCountK(l1, 2));
//        System.out.println(downCountK1(l1, 2));
        System.out.println(removeKNode(l1, 2));
    }


    private static void mergeTwoList() {
        ListNode l1 = new ListNode(1);
        ListNode nextNode1 = new ListNode(2);
        ListNode nextNode2 = new ListNode(3);
        l1.next = nextNode1;
        nextNode1.next = nextNode2;

        ListNode l2 = new ListNode(1);
        ListNode nextNode3 = new ListNode(3);
        ListNode nextNode4 = new ListNode(4);
        l2.next = nextNode3;
        nextNode3.next = nextNode4;
        System.out.println(mergeTwoList(l1, l2));
    }

    private static void mergeKList() {
        ListNode l1 = new ListNode(1);
        ListNode nextNode1 = new ListNode(2);
        ListNode nextNode2 = new ListNode(3);
        l1.next = nextNode1;
        nextNode1.next = nextNode2;

        ListNode l2 = new ListNode(1);
        ListNode nextNode3 = new ListNode(3);
        ListNode nextNode4 = new ListNode(4);
        l2.next = nextNode3;
        nextNode3.next = nextNode4;

        ListNode l3 = new ListNode(2);
        ListNode nextNode5 = new ListNode(5);
        ListNode nextNode6 = new ListNode(6);
        l3.next = nextNode5;
        nextNode5.next = nextNode6;
        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = l1;
        listNodes[1] = l2;
        listNodes[2] = l3;
        System.out.println(mergeKList(listNodes));
    }


    private static ListNode getIntersectionNode(ListNode node1, ListNode node2) {
        ListNode p1 = node1, p2 = node2;
        while (p1 != p2) {
            if (p1 == null) p1 = node2;
            else p1 = p1.next;
            if (p2 == null) p2 = node1;
            else p2 = p2.next;
        }
        return p1;
    }


    private static ListNode detectCycle(ListNode node) {
        ListNode slow = node, fast = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        if (fast == null || fast.next == null) return null;
        slow = node;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    private static boolean hasCycle(ListNode node) {
        ListNode slow = node, fast = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    private static ListNode countMiddle(ListNode node) {
        int count = 1;
        ListNode p = node, p2 = node, p3 = null;
        if (p == null) return null;
        while (p.next != null) {
            p = p.next;
            count++;
        }
        for (int i = 0; i < (count / 2) + 1; i++) {
            p3 = p2;
            p2 = p2.next;
        }
        return p3;
    }


    private static ListNode countMiddle1(ListNode node) {
        if (node == null) return null;
        ListNode fast = node, slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode removeKNode(ListNode node, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = node;
        ListNode listNode = downCountK(dummy, k + 1);
        listNode.next = listNode.next.next;
        return dummy.next;
    }


    private static ListNode downCountK(ListNode node, int k) {
        //最暴力的解题方式，使用两次for循环
        int count = 1;
        if (node == null) return null;
        ListNode p = node, p2 = node, p3 = null;
        while (p.next != null) {
            p = p.next;
            count++;
        }
        for (int i = 0; i < count - k; i++) {
            p3 = p2.next;
            p2 = p2.next;
        }
        return p3;
    }

    private static ListNode downCountK1(ListNode node, int k) {
        ListNode p1 = node, p2 = node;
        //逆向思维，遍历n次即可获取答案
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p2;
    }


    private static ListNode mergeKList(ListNode[] nodeList) {
        if (nodeList.length == 0) return null;
        ListNode dummy = new ListNode(-1), p = dummy;
        PriorityQueue<ListNode> listNodes = new PriorityQueue<>(nodeList.length, Comparator.comparingInt(n -> n.val));
        for (ListNode head : nodeList) {
            if (head != null)
                listNodes.add(head);
        }
        while (!listNodes.isEmpty()) {
            ListNode node = listNodes.poll();
            p.next = node;
            if (node.next != null) {
                listNodes.add(node.next);
            }
            p = p.next;
        }
        return dummy.next;
    }


    private static ListNode mergeTwoList(ListNode node1, ListNode node2) {
        //采用虚拟头节点可以降低
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode n1 = node1, n2 = node2;
        while (n1 != null && n2 != null) {
            if (n1.val > n2.val) {
                p.next = n2;
                n2 = n2.next;
            } else {
                p.next = n1;
                n1 = n1.next;
            }
            p = p.next;
        }
        if (n1 != null) {
            p.next = n1;
        }
        if (n2 != null) {
            p.next = n2;
        }
        return dummy.next;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode next = null;
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
}
