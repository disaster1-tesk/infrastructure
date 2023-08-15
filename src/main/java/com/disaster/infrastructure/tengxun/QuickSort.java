package com.disaster.infrastructure.tengxun;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{38, 21, 87, 65, 24, 19, 34, 58};
        int i = middleThree(nums, 0, nums.length / 2, nums.length - 1);
        swap(nums, 0, i);
        quickSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.println("num = " + num);
        }
    }

    public static int middleThree(int[] nums, int left, int middle, int right) {
        if ((nums[left] < nums[middle]) ^ (nums[left] < nums[right])) return left;
        else if ((nums[middle] < nums[right]) ^ (nums[middle] < nums[right])) return middle;
        return right;
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    public static int partition(int[] nums, int left, int right) {
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left]) j--;
            while (i < j && nums[i] <= nums[left]) i++;
            swap(nums, i, j);
        }
        swap(nums, left, i);
        return i;
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
