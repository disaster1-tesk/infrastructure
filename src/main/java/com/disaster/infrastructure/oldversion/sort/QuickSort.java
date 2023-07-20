package com.disaster.infrastructure.oldversion.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {42, 2, 72, 113, 11, 23};
        quickSort(array, 0, array.length - 1);
        System.out.println("排序后的结果");
        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] ints, int low, int high) {
        if (low >= high) return;
        int partition = partition(ints, low, high);
        quickSort(ints, low, partition - 1);
        quickSort(ints, partition + 1, high);
    }

    private static int partition(int[] ints, int low, int high) {
        int pivot = ints[high];
        int pointer = low;
        for (int i = low; i < high; i++) {
            if (ints[i] <= pivot) {
                if (i != pointer) {
                    int temp = ints[i];
                    ints[i] = ints[pointer];
                    ints[pointer] = temp;
                }
                pointer++;
            }
        }
        int temp = ints[pointer];
        ints[pointer] = ints[high];
        ints[high] = temp;
        return pointer;
    }
}
