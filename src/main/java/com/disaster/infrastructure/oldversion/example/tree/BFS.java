package com.disaster.infrastructure.oldversion.example.tree;

import java.util.LinkedList;
import java.util.List;

public class BFS {
    public static void main(String[] args) {
        kthSmallest();
        isValidBST();
        isInBTS();
        deleteBTS();
        insertBTS();
        numTrees();
        generateTrees();
        findSum();
        codec();
    }

    private static int res = 0;
    private static int rank = 0;
    private static int[][] memo;
    private static int maxSum = 0;
    private static int sum = 0;

    private static void kthSmallest() {
        Node node = new Node(5);
        Node node1 = new Node(3);
        Node node2 = new Node(6);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(1);
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        node.left = node1;
        node.right = node2;
        System.out.println(kthSmallest(node, 2));
    }

    private static void isValidBST() {
        Node node = new Node(5);
        Node node1 = new Node(3);
        Node node2 = new Node(6);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(1);
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        node.left = node1;
        node.right = node2;
        System.out.println(isValidBST(node));
    }

    private static void isInBTS() {
        Node node = new Node(4);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node2.left = node1;
        node2.right = node3;
        node5.left = node4;
        node5.right = node6;
        node.left = node2;
        node.right = node5;
        System.out.println(isInBST(node, 2));
    }

    private static void deleteBTS() {
        Node node = new Node(4);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node2.left = node1;
        node2.right = node3;
        node5.left = node4;
        node5.right = node6;
        node.left = node2;
        node.right = node5;
        System.out.println(deleteBTS(node, 2));
    }

    private static void insertBTS() {
        Node node = new Node(4);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node2.left = node1;
        node2.right = node3;
        node5.left = node4;
        node5.right = node6;
        node.left = node2;
        node.right = node5;
        System.out.println(insertBTS(node, 8));
    }

    /**
     * 灵魂三问：
     * 1.这个函数的定义是什么？
     * 2.这个函数的变量是什么，分别是干什么的？
     * 3.得到递归结果你该干什么？
     */
    private static Node lowestCommonAncestor(Node root, Node p, Node q) {
        //base case  拿到结果该干什么？判断
        if (root == null) return null;
        if (p == root || q == root) return root;
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);
        //函数的定义是什么？
        //1.如果p、q都在同一个root的树下（注意此时是后序遍历，是自底向上）
        if (left != null && right != null) {
            return root;
        }
        //2.如果p、q两个都不在root的树下
        if (left == null && right == null) {
            return null;
        }
        //3.如果p、q有一个在root的树下
        return left == null ? right : left;
    }


    private static int maxSumBST(Node root) {
        traverse(root);
        return maxSum;
    }

    private static void traverse(Node root) {
        if (root == null) {
            return;
        }
        if (!isBST(root.left) || !isBST(root.right)) {
            traverse(root.left);
            traverse(root.right);
        }
        // 计算左子树的最大值和右子树的最小值
        int leftMax = findMax(root.left);
        int rightMin = findMin(root.right);
        // 判断以 root 节点为根的树是不是 BST
        if (root.val <= leftMax || root.val >= rightMin) {
            traverse(root.left);
            traverse(root.right);
        }
        // 如果条件都符合，计算当前 BST 的节点之和
        int leftSum = findSum(root.left);
        int rightSum = findSum(root.right);
        int rootSum = leftSum + rightSum + root.val;
        // 计算 BST 节点的最大和
        maxSum = Math.max(maxSum, rootSum);
    }

    /* 计算以 root 为根的二叉树的最大值 */
    private static int findMax(Node root) {
        if (root == null) return 0;
        while (root != null) {
            root = root.right;
        }
        return root.val;
    }

    /* 计算以 root 为根的二叉树的最小值 */
    private static int findMin(Node root) {
        if (root == null) return 0;
        while (root != null) {
            root = root.left;
        }
        return root.val;
    }

    private static void findSum() {
        Node node = new Node(4);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node2.left = node1;
        node2.right = node3;
        node5.left = node4;
        node5.right = node6;
        node.left = node2;
        node.right = node5;
        System.out.println(findSum(node));
    }

    /* 计算以 root 为根的二叉树的节点和 */
    private static int findSum(Node root) {
        if (root == null) return 0;
        sum += root.val;
        findSum(root.left);
        findSum(root.right);
        return sum;
    }

    /* 判断以 root 为根的二叉树是否是 BST */
    private static boolean isBST(Node root) {
        return isBST(root, null, null);
    }

    private static boolean isBST(Node root, Node minNode, Node maxNode) {
        if (root == null) return false;
        if (minNode != null && root.val <= minNode.val) return false;
        if (maxNode != null && root.val >= maxNode.val) return false;
        return isBST(root.left, minNode, root) && isBST(root.right, root, maxNode);
    }

    private static void generateTrees() {
        System.out.println(generateTrees(4));
    }

    private static void numTrees() {
        System.out.println(numTrees(4));
    }

    private static List<Node> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return build(1, n);
    }

    private static List<Node> build(int lo, int hi) {
        LinkedList<Node> res = new LinkedList<>();
        if (lo > hi) {
            res.add(null);
            return res;
        }
        for (int i = lo; i <= hi; i++) {
            List<Node> leftTree = build(lo, i - 1);
            List<Node> rightTree = build(i + 1, hi);
            for (Node left : leftTree) {
                for (Node right : rightTree) {
                    Node root = new Node(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }


    private static int numTrees(int num) {
        memo = new int[num + 1][num + 1];
        return count(1, num);
    }

    private static int count(int lo, int hi) {
        if (lo > hi) return 1;
        int res = 0;
        // 查备忘录
        if (memo[lo][hi] != 0) return memo[lo][hi];
        for (int i = lo; i <= hi; i++) {
            int left = count(lo, i - 1);
            int right = count(i + 1, hi);
            res += left * right;
        }
        // 将结果存入备忘录
        memo[lo][hi] = res;
        return res;
    }


    private static Node insertBTS(Node root, int val) {
        if (root == null) return new Node(val);
        if (root.val > val) root.left = insertBTS(root.left, val);
        if (root.val < val) root.right = insertBTS(root.right, val);
        return root;
    }


    private static Node deleteBTS(Node root, int val) {
        if (root == null) return null;
        if (root.val == val) {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            Node min = getMin(root.right);
            root.val = min.val;
            root.right = deleteBTS(root.right, min.val);
        } else if (root.val > val) {
            root.left = deleteBTS(root.left, val);
        } else if (root.val < val) {
            root.right = deleteBTS(root.right, val);
        }
        return root;
    }

    private static Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }


    private static boolean isInBST(Node root, int target) {
        if (root == null) return false;
        if (root.val == target) return true;
        if (root.val > target) {
            return isInBST(root.left, target);
        }
        if (root.val < target) {
            return isInBST(root.right, target);
        }
        return false;
    }


    private static boolean isValidBST(Node root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(Node root, Node maxNode, Node minNode) {
        if (root == null) return true;
        if (minNode != null && root.val <= minNode.val) return false;
        if (maxNode != null && root.val >= maxNode.val) return false;
        return isValidBST(root.left, minNode, root) && isValidBST(root.right, root, maxNode);
    }


    private static int kthSmallest(Node root, int k) {
        traverse(root, k);
        return res;
    }


    private static void traverse(Node root, int k) {
        if (root == null) return;
        traverse(root.left, k);
        rank++;
        if (rank == k) {
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }

    private static void codec() {
        Codec codec = new Codec();
        Node node = new Node(4);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node2.left = node1;
        node2.right = node3;
        node5.left = node4;
        node5.right = node6;
        node.left = node2;
        node.right = node5;
        String serialize = codec.serialize(node);
        System.out.println("serialize = " + serialize);
        Node deserialize = codec.deserialize(serialize);
        System.out.println("deserialize = " + deserialize);

        String serialize1 = codec.serializeByPost(node);
        System.out.println("serialize1 = " + serialize1);
        Node deserialize1 = codec.deserializeByPost(serialize1);
        System.out.println("deserialize1 = " + deserialize1);

        String serialize2 = codec.serializeByPost(node);
        System.out.println("serialize2 = " + serialize2);
        Node deserialize2 = codec.deserializeByPost(serialize2);
        System.out.println("deserialize2 = " + deserialize2);

    }

    private static class Codec {
        private static String NULL = "#";
        private static String SEP = ",";

        public String serialize(Node root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        public void serialize(Node root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            sb.append(root.val).append(SEP);
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        public String serializeByPost(Node root) {
            StringBuilder sb = new StringBuilder();
            serializeByPost(root, sb);
            return sb.toString();
        }

        public void serializeByPost(Node root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            serializeByPost(root.left, sb);
            serializeByPost(root.right, sb);
            sb.append(root.val).append(SEP);
        }

        public String serializeByHierarchy(Node root) {
            if (root == null) return "";
            StringBuilder sb = new StringBuilder();
            LinkedList<Node> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                Node cur = q.poll();
                if (cur == null) {
                    sb.append(NULL).append(SEP);
                    continue;
                }
                sb.append(cur.val).append(SEP);
                q.offer(root.left);
                q.offer(root.right);
            }
            return sb.toString();
        }

        public Node deserializeByHierarchy(String data) {
            if (data.isEmpty()) return null;
            String[] nodes = data.split(SEP);
            Node root = new Node(Integer.valueOf(nodes[0]));
            LinkedList<Node> q = new LinkedList<>();
            q.offer(root);
            for (int i = 1; i < nodes.length; ) {
                Node parent = q.poll();
                String left = nodes[i++];
                if (!left.equals(NULL)) {
                    parent.left = new Node(Integer.valueOf(left));
                    q.offer(parent.left);
                } else {
                    parent.left = null;
                }
                String right = nodes[i++];
                if (!right.equals(NULL)) {
                    parent.right = new Node(Integer.valueOf(right));
                    q.offer(parent.right);
                } else {
                    parent.right = null;
                }
            }
            return root;
        }


        public Node deserialize(String data) {
            LinkedList<String> nodes = new LinkedList<>();
            for (String s : data.split(SEP)) {
                nodes.add(s);
            }
            return deserialize(nodes);
        }

        public Node deserialize(LinkedList<String> nodes) {
            if (nodes.isEmpty()) return null;
            String first = nodes.removeFirst();
            if (first.equals(NULL)) return null;
            Node root = new Node(Integer.valueOf(first));
            root.left = deserialize(nodes);
            root.right = deserialize(nodes);
            return root;
        }


        public Node deserializeByPost(String data) {
            LinkedList<String> nodes = new LinkedList<>();
            for (String s : data.split(SEP)) {
                nodes.add(s);
            }
            return deserializeByPost(nodes);
        }

        public Node deserializeByPost(LinkedList<String> nodes) {
            if (nodes.isEmpty()) return null;
            String last = nodes.removeLast();
            if (last.equals(NULL)) return null;
            Node root = new Node(Integer.valueOf(last));
            root.right = deserializeByPost(nodes);
            root.left = deserializeByPost(nodes);
            return root;
        }


    }

    private static class Node {
        private int val;
        private Node left;
        private Node right;


        public Node(int val) {
            this.val = val;
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
                    '}';
        }
    }

}
