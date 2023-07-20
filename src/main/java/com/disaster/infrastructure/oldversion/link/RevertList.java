package com.disaster.infrastructure.oldversion.link;

public class RevertList {
    public static void main(String[] args) {

    }

    /**
     * 永远不要跳进递归逻辑里面去，脑子根本承受不住！！、base case也一定要判断
     *
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 非递归反转列表的方法
     *
     * @param head
     * @return
     */
    public static Node reverse1(Node head) {
        Node pre = null, cur = head, nxt = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }


}
