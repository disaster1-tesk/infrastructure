package com.disaster.infrastructure.oldversion.example.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 树的遍历总共分为深度遍历与广度遍历
 */
public class TreeTraversal {
    public static void main(String[] args) {
        preOrder();
    }
    
    private static void preOrder(){
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node.left = node1;
        node.right = node2;
//        preOrder(node);
        inOrder(node);
        List<Integer> integers = preOrderTraversal(node);
        System.out.println("integers = " + integers);
    }
    

    /**
     * 前序遍历： 根-左-右
     *
     * @param treeNode
     */
    private static void preOrder(TreeNode treeNode) {
        if (treeNode == null) return;
        System.out.println(treeNode.val);
        //遍历左子树
        preOrder(treeNode.left);
        //遍历右子树
        preOrder(treeNode.right);
    }

    private static void inOrder(TreeNode treeNode) {
        if (treeNode == null) return;
        //遍历左子树
        preOrder(treeNode.left);
        System.out.println(treeNode.val);
        //遍历右子树
        preOrder(treeNode.right);
    }

    private static List<Integer> inOrderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while(!stack.isEmpty() || curNode != null) {

            //这一部分实现了递归添加左节点的作用。
            //首先遍历左子节点，包括根节点都入栈，由于是中序遍历，所以根节点在左子树全部
            //节点出栈完毕之后跟着出栈，然后根节点的右子树再走一遍这个相同循环流程
            while(curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }

            //这一部分实现了对根节点的遍历，同时将指针指向了右子树，在下轮中遍历右子树。
            //每次出栈即遍历完一个节点，需要把当前节点的指针移动到右子节点，不管当前节点
            //的右子节点是否为null。如果是null，下次循环就直接走到出栈流程，把当前节点的
            //根节点弹出（此处以左子节点为例：根节点早于左子节点入栈），此时该
            //根节点刚好有右子节点，指针移动到右子节点，接着继续执行相同的循环。直到当前
            //节点和栈都为空，表明遍历结束。
            if(!stack.isEmpty()) {
                curNode = stack.pop();
                list.add(curNode.val);
                curNode = curNode.right;
            }
        }
        return list;
    }


    private static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();

        //第一步：将根节点压入栈中
        stack.push(root);
        TreeNode curNode;
        while (!stack.isEmpty()) {
            //第二步：将根节点弹出栈，并将值加入List集合，根节点遍历完成
            curNode = stack.pop();
            list.add(curNode.val);

            /*
            第三步：根据深度优先搜索二叉树的先根遍历规则，需要先遍历左子树，然后才是右子树
            结合栈的FILO 先入后出原则，先压右子节点，再压左子节点。
            当再次循环弹出顶部元素时，最先弹出的就是左子节点，此时由于是深入优先，所以右子
            节点继续乖乖呆在栈底待命，直到左子树所有节点都入栈并出栈之后，最后才弹出第一次
            压入栈底的右子节点，最后才是对右子树进行遍历
            */
            if (curNode.right != null) {
                stack.add(curNode.right);
            }

            //第四步：压入左子节点到栈顶，等待下一次循环时首先弹出得到遍历
            if (curNode.left != null) {
                stack.add(curNode.left);
            }
        }
        return list;
    }


    
    private static class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private TreeNode next;
        private int val;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, TreeNode right, TreeNode next) {
            this.left = left;
            this.right = right;
            this.next = next;
        }

        public TreeNode(TreeNode left, TreeNode right, TreeNode next, int val) {
            this.left = left;
            this.right = right;
            this.next = next;
            this.val = val;
        }

        public TreeNode(TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
        }

        public TreeNode() {
        }

        
        @Override
        public String toString() {
            return "TreeNode{" +
                    "left=" + left +
                    ", right=" + right +
                    ", next=" + next +
                    '}';
        }
    }
}
