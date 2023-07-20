package com.disaster.infrastructure.oldversion.example.singlelink;

public class CycleList {
    private static ListNode left;

    public static void main(String[] args) {
        isPalindrome();
    }

    private static void isPalindrome() {
        ListNode head = new ListNode(1);
        ListNode ntx = new ListNode(2);
        ListNode ntx1 = new ListNode(2);
        ListNode ntx2 = new ListNode(3);
        head.next = ntx;
        ntx.next = ntx1;
        ntx1.next = ntx2;
        System.out.println(isPalindrome(head));
    }



    private static boolean isPalindrome(ListNode right) {
        left = right;
        return reverse(right);
    }


    private static boolean reverse(ListNode right) {
        if (right == null) return true;
        boolean res = reverse(right.next);
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }

    private static ListNode reverse2(ListNode head) {
        ListNode pre = null, cur = head, nxt = head;
        while (cur!=null){
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    private static boolean reverse1(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) slow = slow.next;
        ListNode left = head;
        ListNode right = reverse2(slow);
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
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
