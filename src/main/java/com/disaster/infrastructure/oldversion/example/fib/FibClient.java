package com.disaster.infrastructure.oldversion.example.fib;

import java.util.Vector;

public class FibClient {
    public static void main(String[] args) {
        System.out.println(fib(20));
        System.out.println(fibByte(0));
        System.out.println(fibTable(20));
        System.out.println(fibTable2(20));
    }

    /**
     * 暴力解法
     *
     * @param n
     * @return
     */
    private static int fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        return fib(n - 1) + fib(n - 2) % 1000000007;
    }

    /**
     * 自顶向下的解法
     *
     * @param n
     * @return
     */
    private static int fibByte(int n) {
        int[] temp = new int[n + 1];
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        temp[0] = 0;
        temp[1] = 1;
        temp[2] = 1;
        if (temp[n] != 0) return temp[n];
        temp[n] = fibByte(n - 1) + fibByte(n - 2);
        return temp[n];
    }

    /**
     * 自底向上的解法
     *
     * @param n
     * @return
     */
    private static int fibTable(int n) {
        Vector<Integer> ints = new Vector<>(n + 1);
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        ints.add(0, 0);
        ints.add(1, 1);
        ints.add(2, 1);
        for (int i = 3; i <= n; i++) {
            ints.add(i, ints.get(i - 1) + ints.get(i - 2));
        }
        return ints.get(n);
    }

    /**
     * 对自底向上解法的空间优化
     * @param n
     * @return
     */
    private static int fibTable2(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        int curr = 1;
        int pre = 1;
        for (int i = 3; i <= n; i++) {
            int sum = curr + pre;
            pre = curr;
            curr = sum;
        }
        return curr;
    }


}
