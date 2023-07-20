package com.disaster.infrastructure.oldversion.example.coins;

import java.util.Arrays;

/**
 * 凑硬币
 */
public class CoinsClient {

    public static void main(String[] args) {
        int[] ints = new int[3];
        ints[0] = 1;
        ints[1] = 2;
        ints[2] = 5;
        int amount = 20;
        System.out.println(coinChance(ints, amount));
        System.out.println(coinChanceTable(ints, amount, new int[amount + 1]));
        int[] ints1 = new int[2];
        ints1[0] = 2;
        ints1[1] = 4;
        System.out.println(coinChanceTable1(ints1, 22));
    }

    /**
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
        for (int coin : coins) {
            int sumProblem = coinChance(coins, amount - coin);
            if (sumProblem == -1) continue;
            res = Math.min(res, sumProblem + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 自顶向下
     *
     * @param coins
     * @param amount
     * @param ints
     * @return
     */
    private static int coinChanceTable(int[] coins, int amount, int[] ints) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (ints[amount] != 0) {
            return ints[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sumProblem = coinChanceTable(coins, amount - coin, ints);
            if (sumProblem == -1) continue;
            res = Math.min(res, sumProblem + 1);
        }
        ints[amount] = res;
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 自底向上
     *
     * @param coins
     * @param amount
     * @return
     */
    private static int coinChanceTable1(int[] coins, int amount) {
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
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

}
