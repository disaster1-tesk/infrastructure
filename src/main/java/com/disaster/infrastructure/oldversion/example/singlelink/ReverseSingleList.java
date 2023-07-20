package com.disaster.infrastructure.oldversion.example.singlelink;

public class ReverseSingleList {
    public static void main(String[] args) {
        reverse();
        reverseN();
        reverseBetween();
    }

    private static void reverse() {
        ListNode l1 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        l1.next = node1;
        node1.next = node2;
        System.out.println(reverse(l1));
    }

    private static void reverseN() {
        ListNode l1 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        l1.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(reverseN(l1, 4, null));
    }

    private static void reverseBetween() {
        ListNode l1 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        l1.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(reverseBetween(l1, 3, 4));
    }

    private static ListNode reverse(ListNode node) {
        if (node.next == null) return node;
        ListNode last = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return last;
    }

    private static ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n, null);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    private static ListNode reverseN(ListNode head, int n, ListNode successor) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1, successor);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
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
