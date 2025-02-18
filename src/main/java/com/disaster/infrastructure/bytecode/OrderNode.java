package com.disaster.infrastructure.bytecode;


public class OrderNode {


    public static void main(String[] args) {

    }


    public static ListNode orderListNode(ListNode root) {
        if (root == null) return null;
        ListNode dummy = new ListNode(-1);
        Integer min = null;
        int count = 0;
        //获取链条长度
        while (root.next != null) {
            count++;
            root = root.next;
        }
        ListNode temp;
        for (int i = 0; i < count; i++) {
            temp = null;
            for (int j = 0; j < count; j++) {
                if (temp == null) {
                    temp = root;
                }else {
                    if (min == null){
                        min = temp.val;
                    }else {
                        if (min > temp.val){
                            min = temp.val;
                        }
                    }
                }
            }
            dummy.next = new ListNode(min);
        }
        return dummy.next;
    }

    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
