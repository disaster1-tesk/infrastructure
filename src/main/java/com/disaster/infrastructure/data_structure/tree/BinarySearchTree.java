package com.disaster.infrastructure.data_structure.tree;

/**
 * simply implement a BinarySearchTree
 *
 * @author disaster
 * @param <E>
 * @version 1.0
 */
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
        // 若树为空，直接提前返回
        if (root == null)
            return;
        TreeNode<E> cur = root, pre = null;
        // 循环查找，越过叶节点后跳出
        while (cur != null) {
            // 找到待删除节点，跳出循环
            if (cur.val == element)
                break;
            pre = cur;
            // 待删除节点在 cur 的右子树中
            if (cur.val.hashCode() < element.hashCode())
                cur = cur.right;
                // 待删除节点在 cur 的左子树中
            else
                cur = cur.left;
        }
        // 若无待删除节点，则直接返回
        if (cur == null)
            return;
        // 子节点数量 = 0 or 1
        if (cur.left == null || cur.right == null) {
            // 当子节点数量 = 0 / 1 时， child = null / 该子节点
            TreeNode<E> child = cur.left != null ? cur.left : cur.right;
            // 删除节点 cur
            if (cur != root) {
                if (pre.left == cur)
                    pre.left = child;
                else
                    pre.right = child;
            } else {
                // 若删除节点为根节点，则重新指定根节点
                root = child;
            }
        }
        // 子节点数量 = 2
        else {
            // 获取中序遍历中 cur 的下一个节点
            TreeNode<E> tmp = cur.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            // 递归删除节点 tmp
            remove(tmp.val);
            // 用 tmp 覆盖 cur
            cur.val = tmp.val;
        }
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
