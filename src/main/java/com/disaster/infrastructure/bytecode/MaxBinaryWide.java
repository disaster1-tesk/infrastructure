package com.disaster.infrastructure.bytecode;


public class MaxBinaryWide {
    private static int maxWidth;

    public static void main(String[] args) {

    }


    //描述：设计一个微信朋友圈，要求能看自己的朋友圈、看好友的朋友圈、发朋友圈以及新朋友圈的入口红点。
    //  persons(person_id, name)
    //  orders(order_id, price, person_id)
    //  计算出订单总费用最高的人的姓名
    // select a.name,sum(price) prices from persons a inner join orders b on a.person_id = b.person_id
    // group by a.person_id order by prices limit 1
    private class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        /**
         * Instantiates a new Tree node.
         *
         * @param val   the val
         * @param left  the left
         * @param right the right
         */
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
