package com.disaster.infrastructure.data_structure.tree;


public class BinarySearchTree<E> {

    private TreeNode<E> root;

    public BinarySearchTree(E element) {
        this.root = new TreeNode<E>(element, null, null);
    }

    public TreeNode<E> search(E e) {
        TreeNode<E> cur = root;
        while (cur != null) {
            if (cur.val.hashCode() > e.hashCode()) {
                cur = cur.left;
            } else if (cur.val.hashCode() < e.hashCode()) {
                cur = cur.right;
            } else {
                break;
            }
        }
        return cur;
    }

    public void insert(E element) {
        if (root == null) return;
        TreeNode<E> cur = root, pre = null;
        while (cur != null) {
            if (cur.val.equals(element)) return;
            pre = cur;
            if (cur.val.hashCode() > element.hashCode()) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        TreeNode<E> eTreeNode = new TreeNode<>(element, null, null);
        if (pre.val.hashCode() < element.hashCode()) {
            pre.right = eTreeNode;
        } else {
            pre.left = eTreeNode;
        }
    }


    public void remove(E element) {

    }


    private class TreeNode<E> {
        private E val;
        private TreeNode<E> left;
        private TreeNode<E> right;

        /**
         * Instantiates a new Tree node.
         *
         * @param val   the val
         * @param left  the left
         * @param right the right
         */
        public TreeNode(E val, TreeNode<E> left, TreeNode<E> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }
}
