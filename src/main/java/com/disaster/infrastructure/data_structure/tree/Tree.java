package com.disaster.infrastructure.data_structure.tree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Tree.
 *
 * @author disaster
 * @version 1.0
 */
public class Tree {
    /**
     * 广度遍历，通常需要借助队列实现
     *
     * @param root the root
     * @return the list
     */
    List<Object> levelOrder(TreeNode root) {
        // 初始化队列，加入根节点
        Queue<TreeNode> queue = new LinkedList() {{
            add(root);
        }};
        // 初始化一个列表，用于保存遍历序列
        List<Object> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // 队列出队
            list.add(node.val);           // 保存节点值
            if (node.left != null)
                queue.offer(node.left);   // 左子节点入队
            if (node.right != null)
                queue.offer(node.right);  // 右子节点入队
        }
        return list;
    }

    /**
     * 深度优先算法之前序遍历
     *
     * @param root
     * @param result
     */
    void preOrder(TreeNode root, List result) {
        if (root == null)
            return;
        result.add(root.val);
        preOrder(root.left, result);
        preOrder(root.right, result);
    }

    /**
     * 深度优先算法之中序遍历
     *
     * @param root
     * @param result
     */
    void inOrder(TreeNode root, List result) {
        if (root == null)
            return;
        preOrder(root.left, result);
        result.add(root.val);
        preOrder(root.right, result);
    }

    /**
     * 深度优先算法之后序遍历
     *
     * @param root
     * @param result
     */
    void postOrder(TreeNode root, List result) {
        if (root == null)
            return;
        preOrder(root.left, result);
        preOrder(root.right, result);
        result.add(root.val);
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
