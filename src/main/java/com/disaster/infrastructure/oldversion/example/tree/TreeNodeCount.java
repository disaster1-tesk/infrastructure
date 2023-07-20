package com.disaster.infrastructure.oldversion.example.tree;

/**
 * 普通二叉树、完全二叉树、满二叉树这三者的区别需要我们关注一下：
 * 1.普通二叉树：没什么好说的，什么情况都有可能
 * 2.完全二叉树：紧凑靠左排列
 * 3.满二叉树：每层都是满的
 */
public class TreeNodeCount {
    public static void main(String[] args) {

    }

    private int normalTreeCount(TreeNode root) {
        if (root == null) return 0;
        int left = normalTreeCount(root.left);
        int right = normalTreeCount(root.right);
        return 1 + left + right;
    }

    private int PerfectTreeCount(TreeNode root) {
        int num = 0;
        while (root != null) {
            root = root.left;
            num += 1;
        }
        return (int) (Math.pow(2, num) - 1);
    }

    private int FullTreeCount(TreeNode root) {
        TreeNode l = root, r = root;
        int h1 = 0, h2 = 0;
        while (l != null) {
            l = l.left;
            h1 += 1;
        }
        while (r != null) {
            r = r.right;
            h2 += 1;
        }
        if (h1 == h2) return (int) (Math.pow(2, h1) - 1);
        return 1 + FullTreeCount(root.left) + FullTreeCount(root.right);
    }

    private class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
