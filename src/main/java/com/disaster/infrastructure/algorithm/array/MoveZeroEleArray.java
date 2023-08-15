package com.disaster.infrastructure.algorithm.array;

/**
 * title:
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * resolver line:
 * 快慢指针，先将等于0的数据进行删除，然后将数组剩余的空间补全响应的数据
 *
 * @author disaster
 * @version 1.0
 */
public class MoveZeroEleArray {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 5, 5, 6};
        moveZerosEle(nums);
        for (int num : nums) {
            System.out.println("num = " + num);
        }
    }

    public static void moveZerosEle(int[] nums) {
        int i = removeEle(nums, 0);
        for (; i < nums.length; i++) {
            nums[i] = 0;
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
