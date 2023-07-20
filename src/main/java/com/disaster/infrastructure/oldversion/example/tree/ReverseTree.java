package com.disaster.infrastructure.oldversion.example.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* 二叉树遍历框架
void traverse(TreeNode root) {
        // 前序遍历
        traverse(root.left)
        // 中序遍历
        traverse(root.right)
        // 后序遍历
        }
*/
public class ReverseTree {
    public static void main(String[] args) {
        invertTree();
        connectTwoNode();
        flatten();
        constructMaximumBinaryTree();
        buildTree();
        buildTree2();
        countTreeNode();
        recordRepNode();
    }

    private static void invertTree() {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node.left = node1;
        node.right = node2;
        System.out.println(invertTree(node));
    }

    private static void connectTwoNode() {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node.left = node1;
        node.right = node2;
        System.out.println(connectTwoNode(node));
    }

    private static void flatten() {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node.left = node1;
        node.right = node2;
        flatten(node);
        System.out.println(node);
    }


    private static void buildTree() {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder));
    }

    private static void buildTree2() {
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        System.out.println(buildTree2(inorder, postorder));
    }

    private static Node buildTree2(int[] inorder, int[] postorder) {
        return build2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private static void countTreeNode() {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node.left = node1;
        node.right = node2;
        System.out.println(countTreeNode(node));
    }


    private static void recordRepNode(){
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(2);
        Node node5 = new Node(4);
        Node node6 = new Node(4);
        node1.left = node3;
        node4.left = node5;
        node2.left = node4;
        node2.right = node6;
        node.left = node1;
        node.right = node2;
        HashMap<String, Integer> map = new HashMap<>();
        LinkedList<Node> nodes = new LinkedList<>();
        recordRepNode(node,map,nodes);
        System.out.println("nodes = " + nodes);
    }


    private static String recordRepNode(Node node, Map<String, Integer> map, List<Node> res) {
        // 对于空节点，可以用一个特殊字符表示
        if (node == null) {
            return "#";
        }
        String left = recordRepNode(node.left, map, res);
        String right = recordRepNode(node.right, map, res);

        String subTree = left + "," + right + "," + node.val;


        Integer orDefault = map.getOrDefault(subTree, 0);
        if (orDefault == 1) {
            res.add(node);
        }

        map.put(subTree,orDefault+1);
        return subTree;
    }


    private static int countTreeNode(Node root) {
        if (root == null) return 0;
        int left = countTreeNode(root.left);
        int right = countTreeNode(root.right);
        int res = left + right + 1;
        return res;
    }


    private static Node build2(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) return null;
        int rootVal = postorder[postEnd];
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        Node node = new Node(rootVal);
        int leftSize = index - inStart;
        node.left = build2(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1);
        node.right = build2(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
        return node;
    }


    private static Node buildTree(int[] preOrder, int[] inOrder) {
        return build(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    private static Node build(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        int rootVal = preOrder[preStart];
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == rootVal) {
                index = i;
                break;
            }
        }
        Node root = new Node(rootVal);
        int leftSize = index - inStart;
        root.left = build(preOrder, preStart + 1, preStart + leftSize, inOrder, inStart, index - 1);
        root.right = build(preOrder, preStart + leftSize + 1, preEnd, inOrder, index + 1, inEnd);
        return root;
    }


    private static void constructMaximumBinaryTree() {
        int[] ints = new int[]{3, 2, 1, 6, 0, 5};
        Node node = constructMaximumBinaryTree(ints);
        System.out.println("node = " + node);
    }

    private static Node constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private static Node build(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int maxVal = Integer.MIN_VALUE, index = -1;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] > maxVal) {
                index = i;
                maxVal = nums[i];
            }
        }
        Node root = new Node(maxVal);
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);
        return root;
    }

    private static void flatten(Node root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        Node left = root.left;
        Node right = root.right;

        root.left = null;
        root.right = left;

        Node p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }


    private static Node invertTree(Node tree) {
        if (tree == null) return null;
        Node temp = tree.left;
        tree.left = tree.right;
        tree.right = temp;
        invertTree(tree.left);
        invertTree(tree.right);
        return tree;
    }

    private static Node connectTwoNode(Node node) {
        if (node == null) return null;
        Node left = node.left, right = node.right;
        connectTwoNode(left, right);
        return node;
    }

    private static void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序遍历位置 ****/
        // 将传入的两个节点连接
        node1.next = node2;

        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }

    private static class Node {
        private int val;
        private Node left;
        private Node right;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    ", next=" + next +
                    '}';
        }
    }
}
