package com.disaster.infrastructure.oldversion.example.singlelink;

public class ReverseKList {
    public static void main(String[] args) {
        reverse();
        reverseKGroup();
    }


    private static void reverse() {
        ListNode head = new ListNode(1);
        ListNode nxt = new ListNode(2);
        ListNode nxt1 = new ListNode(3);
        ListNode nxt2 = new ListNode(4);
        head.next = nxt;
        nxt.next = nxt1;
        nxt1.next = nxt2;
        System.out.println(reverse(head));
    }

    private static void reverseKGroup() {
        ListNode head = new ListNode(1);
        ListNode nxt = new ListNode(2);
        ListNode nxt1 = new ListNode(3);
        ListNode nxt2 = new ListNode(4);
        ListNode nxt3 = new ListNode(5);
        head.next = nxt;
        nxt.next = nxt1;
        nxt1.next = nxt2;
        nxt2.next = nxt3;
        System.out.println(reverseKGroup(head,2));
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null, nxt = head, cur = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * 反转区间 [a, b) 的元素，注意是左闭右开
     *
     * @param head
     * @param second
     * @return
     */
    private static ListNode reverse(ListNode head, ListNode second) {
        ListNode pre = null, nxt = head, cur = head;
        while (cur != second) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    private static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b==null) return head;
            b = b.next;
        }
        ListNode reverse = reverse(a, b);
        a.next = reverseKGroup(b,k);
        return reverse;
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
