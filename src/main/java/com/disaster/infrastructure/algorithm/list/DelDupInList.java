package com.disaster.infrastructure.algorithm.list;

/**
 * 题目：与DelDupInArray中的题目一样，只不过换成链表
 * <p>
 * 解题思路：与DelDupInArray同理
 *
 * @author disaster
 * @version 1.0
 */
public class DelDupInList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, null);
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(2, null);
        ListNode node4 = new ListNode(3, null);
        ListNode tail = new ListNode(3, null);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = tail;
        System.out.println(delDupInList(head));
    }


    public static ListNode delDupInList(ListNode head) {
        if (head == null) return head;
        ListNode slow = head, fast = head;
        while (fast.next != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    private static class ListNode {
        private Object val;
        private ListNode next;

        public ListNode(Object val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
