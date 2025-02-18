package com.disaster.infrastructure.algorithm.array;

/**
 * 题目：给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度，不要使用额外的数组空间，你必须在原地修改输入数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组，并在使用o(1)额外空间的条件下完成
 * <p>
 * 解题关键：快慢指针，让fast指针在前面探路，当出现不是重复的数据时，赋值给slow
 *
 * @author disaster
 * @version 1.0
 */
public class DelDupInArray {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0,1,1,2,2,2,3,3,3,3,4,5,5,6};
        int i = findDuplicates(nums);
        for (int j = 0; j < i; j++) {
            System.out.println("nums = " + nums[j]);
        }
    }

    public static int delDupInArray(int[] nums) {
        if (nums.length == 0) return 0;
        int slow = 0, fast = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    public static int findDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int slow = 0, fast = 1;
        for (int i = fast; i < nums.length; i++) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }


    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        int ptr1 = nums[0];
        int ptr2 = slow;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

}
