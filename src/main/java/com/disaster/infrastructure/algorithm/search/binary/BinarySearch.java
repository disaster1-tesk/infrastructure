package com.disaster.infrastructure.algorithm.search.binary;

/**
 * 二分查找
 * <p>
 * Frame:
 * <p>
 * int binarySearch(int[] nums, int target) {
 * int left = 0, right = ...;
 * <p>
 * while(...) {
 * int mid = left + (right - left) / 2;
 * if (nums[mid] == target) {
 * ...
 * } else if (nums[mid] < target) {
 * left = ...
 * } else if (nums[mid] > target) {
 * right = ...
 * }
 * }
 * return ...;
 * }
 *
 * @author disaster
 * @version 1.0
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        System.out.println(binarySearch(nums, 9));
    }

    /**
     * title:
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public static int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length; // right的值怎么取决定区间是否是双闭或者单闭
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;//注意
            } else if (nums[mid] < target) {
                left = mid + 1;//注意
            }else if (nums[mid] > target) {
                right = mid;//注意
            }
        }
        return left;
    }


}
