package com.disaster.infrastructure.algorithm.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/***
 * title:
 *  给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素
 *
 * Solution idea:
 *  快慢指针，slow指针接受不等于val的元素，fast当前头兵
 *
 * @author disaster
 * @version 1.0
 */
public class RemoveEleArray {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 5, 5, 6};
        int i = removeEle(nums, 2);
        int[] ints = Arrays.copyOfRange(nums, 0, i);
        for (int anInt : ints) {
            System.out.println("anInt = " + anInt);
        }
    }

    public static int removeEle(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
