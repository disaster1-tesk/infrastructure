package com.disaster.infrastructure.oldversion.example.fullpermutation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列问题，回溯算法
 */
public class FullPermutation {
    private static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        int[] ints = new int[3];
        ints[0] = 1;
        ints[1] = 2;
        ints[2] = 3;
        fullPerm(ints);
        StringBuilder sb = new StringBuilder();
        for (List<Integer> re : res) {
            for (Integer integer : re) {
                sb.append(integer);
                sb.append(",");
            }
            sb.replace(sb.length() - 1, sb.length(), " ");
        }
        System.out.println("sb = " + sb);
    }

    private static List<List<Integer>> fullPerm(int[] nums) {
        LinkedList<Integer> chooses = new LinkedList<>();
        backtrack(nums, chooses);
        return res;
    }

    private static void backtrack(int[] nums, LinkedList<Integer> chooses) {
        if (chooses.size() == nums.length) {
            res.add(new ArrayList<>(chooses));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (chooses.contains(nums[i])) {
                continue;
            }
            chooses.add(nums[i]);
            backtrack(nums, chooses);
            chooses.removeLast();
        }
    }
}
