package com.disaster.infrastructure.oldversion.link;

public class MiddleList {
    public static void main(String[] args) {

    }

    /**
     * 每当慢指针 slow 前进一步，快指针 fast 就前进两步，这样，当 fast 走到链表末尾时，slow 就指向了链表中点
     *
     * @param head
     * @return
     */
    public static Node middleList(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 如果 fast 最终遇到空指针，说明链表中没有环；如果 fast 最终和 slow 相遇，那肯定是 fast 超过了 slow 一圈，说明链表中含有环。
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回回环节点
     *
     * @param head
     * @return
     */
    public static Node detectCycle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        // 快慢指针同步前进，相交点就是环起点
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 既然「寻找两条链表的交点」的核心在于让 p1 和 p2 两个指针能够同时到达相交节点 c1，那么可以通过预先计算两条链表的长度来做到这一点
     *
     * @param node1
     * @param node2
     * @return
     */
    public static Node getIntersectionNode(Node node1, Node node2) {
        int p1 = 0, p2 = 0;
        // 计算两条链表的长度
        for (Node n1 = node1; n1 != null; n1 = n1.next) {
            p1++;
        }
        for (Node n2 = node2; n2 != null; n2 = n2.next) {
            p2++;
        }
        Node n1 = node1, n2 = node2;
        if (p1 > p2) {
            for (int i = 0; i < p1 - p2; i++) {
                n1 = n1.next;
            }
        } else {
            for (int i = 0; i < p2 - p1; i++) {
                n2 = n2.next;
            }
        }
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    /**
     * 我们可以让 p1 遍历完链表 A 之后开始遍历链表 B，让 p2 遍历完链表 B 之后开始遍历链表 A，这样相当于「逻辑上」两条链表接在了一起。
     * 如果这样进行拼接，就可以让 p1 和 p2 同时进入公共部分，也就是同时到达相交节点
     *
     * @param node1
     * @param node2
     * @return
     */
    public static Node getIntersectionNode1(Node node1, Node node2) {
        Node n1 = node1, n2 = node2;
        while (n1 != n2) {
            if (n1 == null) {
                n1 = n2;
            } else {
                n1 = n1.next;
            }
            if (n2 == null) {
                n2 = n1;
            } else {
                n2 = n2.next;
            }
        }
        return n1;
    }

}
