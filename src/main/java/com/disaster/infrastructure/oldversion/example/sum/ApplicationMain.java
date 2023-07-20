package com.disaster.infrastructure.oldversion.example.sum;

import java.util.*;

public class ApplicationMain {
    private static List<List<Integer>> res = new LinkedList<>();
    private static ListNode temp;

    public static void main(String[] args) {
        fib();
        coin();
        perm();
        sort();
        reverse();
        maxNum();
        constructMaximumBinaryTree();
        flatten();
    }


    private static void reverse() {
        ListNode head = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        head.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
//        System.out.println(reverse(head));
//        System.out.println(reverseN(head, 2, null));
        System.out.println(reverseRange(head, 2, 4));
    }

    private static void sort() {
        int[] nums = new int[5];
        nums[0] = 1;
        nums[1] = 3;
        nums[2] = 0;
        nums[3] = 5;
        nums[4] = 6;
        int[] ints = bubbleSort(nums);
        System.out.println(ints);
    }

    private static void coin() {
        int[] ints = new int[3];
        ints[0] = 2;
        ints[1] = 5;
        ints[2] = 6;
        int amount = 20;
        System.out.println(coinChance(ints, amount));
        System.out.println(coinChanceTable(ints, amount, new int[amount + 1]));
        System.out.println(coinChanceUp(ints, amount));
    }

    private static void fib() {
        System.out.println(fib(20));
        System.out.println(fibByte(20));
        System.out.println(fibTable(20));
        System.out.println(fibTemp(20));
    }

    private static void perm() {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        int[] ints = new int[4];
        ints[0] = 1;
        ints[1] = 2;
        ints[2] = 3;
        ints[3] = 4;
        fullPerm1(ints);
        System.out.println("res = " + res);
    }

    private static void maxNum() {
        int[] ints = new int[4];
        ints[0] = 3;
        ints[1] = 8;
        ints[2] = 1;
        ints[3] = 20;
        System.out.println(maxNum(ints));
    }


    private static void constructMaximumBinaryTree() {
        int[] ints = new int[4];
        ints[0] = 3;
        ints[1] = 8;
        ints[2] = 1;
        ints[3] = 20;
        System.out.println(constructMaximumBinaryTree(ints));
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

    private static Node buildTree1(int[] inorder, int[] postorder) {
        return buildTree1(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private static Node buildTree1(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) return null;

        int rootVal = postorder[postEnd];
        int index = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;
        Node node = new Node(rootVal);
        node.left = buildTree1(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize );
        node.left = buildTree1(inorder, index + 1, inEnd, postorder, postStart + leftSize + 1, postEnd);
        return node;
    }


    private static Node buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static Node buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        int rootVal = preorder[preStart];
        int index = -1;
        //获取中序遍历的根节点
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;
        Node node = new Node(rootVal);
        node.left = buildTree(preorder, preStart, index - 1, inorder, inStart, inStart + leftSize );
        node.right = buildTree(preorder, index + 1, preEnd, inorder, inStart + leftSize + 1, inEnd);
        return node;
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
        Node node = new Node(maxVal);
        node.left = build(nums, lo, index - 1);
        node.right = build(nums, index + 1, hi);
        return node;
    }


    private static int maxNum(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j < prices.length; j++) {
                if ((prices[j] - prices[i]) > result) {
                    result = prices[j] - prices[i];
                }
            }
        }
        return result;
    }


    private static Node invertTree(Node node) {
        if (node == null) return null;
        Node tem = node.left;
        node.left = node.right;
        node.right = tem;
        invertTree(node.left);
        invertTree(node.right);
        return node;
    }

    private static Node connectionTwoNode(Node node) {
        if (node == null) return null;
        Node left = node.left, right = node.right;
        connectionTwoNode(left, right);
        return node;
    }

    private static void connectionTwoNode(Node left, Node right) {
        if (left == null || right == null) return;
        left.next = right;
        connectionTwoNode(left.left, left.right);
        connectionTwoNode(right.left, right.right);
        connectionTwoNode(left.right, right.left);
    }

    private static boolean isPalindrome(ListNode head) {
        temp = head;
        return palindrome(head);
    }


    private static boolean palindrome(ListNode head) {
        if (head == null) return true;
        boolean palindrome = palindrome(head.next);
        palindrome = palindrome && (temp.val == head.val);
        temp = temp.next;
        return palindrome;
    }


    private static ListNode palindromeReverse(ListNode head) {
        ListNode pre = null, nxt = head, cur = head;
        while (cur.next != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return cur;
    }


    private static boolean palindrome1(ListNode head) {
        if (head == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null)
            slow = slow.next;
        ListNode right = head;
        ListNode left = palindromeReverse(slow);
        while (left != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }


    private static int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }


    /**
     * 回溯算法的核心：
     * 路径：也就是已经做出的选择
     * 选择列表：也就是你当前可以做出的选择
     * 结束条件：也就是达到决策树底层，无法再做选择的条件
     * <p>
     * 框架：
     * result = []
     * def backtrack(路径, 选择列表):
     * if 满足结束条件:
     * result.add(路径)
     * return
     * <p>
     * for 选择 in 选择列表:
     * 做选择
     * backtrack(路径, 选择列表)
     * 撤销选择
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> fullPerm(int[] nums) {
        LinkedList<Integer> chooses = new LinkedList<>();
        backTrack(nums, chooses);
        return res;
    }

    private static List<List<Integer>> fullPerm1(int[] nums) {
        LinkedList<Integer> chooses = new LinkedList<>();
        backTrack1(nums, chooses);
        return res;
    }

    private static void backTrack1(int[] nums, LinkedList<Integer> chooses) {
        if (nums.length == chooses.size())
            res.add(new ArrayList<>(chooses));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) continue;
            int choose = nums[i];
            nums[i] = -1;
            chooses.add(choose);
            backTrack1(nums, chooses);
            chooses.removeLast();
            nums[i] = choose;
        }
    }


    private static void backTrack(int[] nums, LinkedList<Integer> chooses) {
        if (chooses.size() == nums.length) res.add(new ArrayList<>(chooses));
        for (int i = 0; i < nums.length; i++) {
            if (chooses.contains(nums[i])) continue;
            chooses.add(nums[i]);
            backTrack(nums, chooses);
            chooses.removeLast();
        }
    }

    /**
     * 动态规划问题的核心（要符合「最优子结构」，子问题间必须互相独立）：
     * 状态
     * 状态转移方程
     * 确定「选择」并择优
     *
     *
     * <p>
     * 自顶向下
     *
     * @param coins
     * @param amount
     * @return
     */
    private static int coinChance(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int i1 = coinChance(coins, amount - coins[i]);
            if (i1 == -1) continue;
            res = Math.min(i1 + 1, res);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 自顶向下
     *
     * @param coins
     * @param amount
     * @param table
     * @return
     */
    private static int coinChanceTable(int[] coins, int amount, int[] table) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;
        if (table[amount] != 0) {
            return table[amount];
        }
        for (int i = 0; i < coins.length; i++) {
            int i1 = coinChance(coins, amount - coins[i]);
            if (i1 == -1) continue;
            res = Math.min(i1 + 1, res);
        }
        table[amount] = res;
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 自底向上
     *
     * @param coins
     * @param amount
     * @return
     */
    private static int coinChanceUp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount];
    }


    /**
     * 自顶向下
     *
     * @param n
     * @return
     */
    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 自顶向下-备忘录
     *
     * @param n
     * @return
     */
    private static int fibByte(int n) {
        int[] ints = new int[n + 1];
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        ints[0] = 0;
        ints[1] = 1;
        ints[2] = 1;
        if (ints[n] != 0) return ints[n];
        ints[n] = fibByte(n - 1) + fibByte(n - 2);
        return ints[n];
    }

    /**
     * 自底向上
     *
     * @param n
     * @return
     */
    private static int fibTable(int n) {
        int[] ints = new int[n + 1];
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        ints[0] = 0;
        ints[1] = 1;
        ints[2] = 1;
        for (int i = 3; i <= n; i++) {
            ints[i] = ints[i - 1] + ints[i - 2];
        }
        return ints[n];
    }

    /**
     * 自底向上
     *
     * @param n
     * @return
     */
    private static int fibTemp(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int curr = 1;
        int pre = 1;
        for (int i = 3; i <= n; i++) {
            int num = curr + pre;
            pre = curr;
            curr = num;
        }
        return curr;
    }

    private static ListNode mergeTwoNode(ListNode node1, ListNode node2) {
        ListNode temp = new ListNode(-1), p3 = temp;
        ListNode p1 = node1, p2 = node2;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p3.next = p2;
                p2 = p2.next;
            } else {
                p3.next = p1;
                p1 = p1.next;
            }
            p3 = p3.next;
        }
        if (p1 != null) {
            p3.next = p1;
        }
        if (p2 != null) {
            p3.next = p2;
        }
        return temp.next;
    }

    private static ListNode mergeKList(ListNode[] nodes) {
        if (nodes.length == 0) return null;
        ListNode dummy = new ListNode(-1), p = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(nodes.length, Comparator.comparingInt(n -> n.val));
        for (ListNode node : nodes) {
            if (node != null)
                queue.add(node);
        }
        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            p.next = poll;
            if (poll.next != null) queue.add(poll.next);
            p = p.next;
        }
        return dummy.next;
    }


    private static ListNode reverse(ListNode head) {
        if (head.next == null) return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    private static ListNode reverseN(ListNode head, int n, ListNode temp) {
        if (n == 1) {
            temp = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1, temp);
        head.next.next = head;
        head.next = temp;
        return last;
    }

    private static ListNode reverseRange(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n, null);
        }
        head.next = reverseRange(head.next, m - 1, n - 1);
        return head;
    }


    private static ListNode getIntersectionNode(ListNode node1, ListNode node2) {
        ListNode p1 = node1, p2 = node2;
        while (p1 != p2) {
            if (p1 == null) p1 = p2;
            else p1 = p1.next;
            if (p2 == null) p2 = p1;
            else p2 = p2.next;
        }
        return p1;
    }

    private static ListNode countMiddle(ListNode head) {
        if (head == null) return null;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode countDownK(ListNode head, int k) {
        ListNode dummy = head, p = head;
        for (int i = 0; i < k; i++) {
            dummy = dummy.next;
        }
        while (dummy != null) {
            dummy = dummy.next;
            p = p.next;
        }
        return p;
    }

    private static boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }

    private static ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        if (fast != null && fast.next != null) return null;
        slow = head;
        while (slow == fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    private static class ListNode {
        private int val;
        private ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode next = null;
            sb.append("[");
            sb.append(val);
            next = this.next;
            while (next != null) {
                sb.append(",");
                sb.append(next.val);
                next = next.next;
            }
            sb.append("]");
            return sb.toString();
        }
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
