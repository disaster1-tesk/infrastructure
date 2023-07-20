package com.disaster.infrastructure.oldversion.datastructure.queue.v4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MonotonicQueuePlus {
    private LinkedList<Integer> queue = new LinkedList<>();
    private volatile int current = 0;
    private ReentrantLock lock = new ReentrantLock();
    private int size = 0;

    public void push(int t) {
        try {
            lock.lock();
            while (!queue.isEmpty() && t > queue.getLast()) {
                queue.pollLast();
                size--;
            }
            queue.addLast(t);
            current = t;
            size++;
        } finally {
            lock.unlock();
        }

    }

    public int max() {
        try {
            lock.lock();
            if (queue.isEmpty()) {
                return -1;
            }
            size--;
            return queue.pollFirst();
        } finally {
            lock.unlock();
        }
    }

    public int min() {
        try {
            lock.lock();
            if (queue.isEmpty()) {
                return -1;
            }
            size--;
            return queue.pollLast();
        } finally {
            lock.unlock();
        }
    }

    public void pop() {
        if (current == queue.getFirst()) {
            queue.pollFirst();
            if (queue.isEmpty()) {
                current = Integer.MIN_VALUE;
            } else {
                current = queue.peekLast();
            }
            size--;
        }
    }

    public int size() {
        return size;
    }


    /* 解题函数的实现 */
    static int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueuePlus window = new MonotonicQueuePlus();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                //先填满窗口的前 k - 1
                window.push(nums[i]);
            } else {
                // 窗口向前滑动，加入新数字
                window.push(nums[i]);
                // 记录当前窗口的最大值
                res.add(window.max());
                // 移出旧数字
                window.pop();
            }
        }
        // 需要转成 int[] 数组再返回
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }
}
