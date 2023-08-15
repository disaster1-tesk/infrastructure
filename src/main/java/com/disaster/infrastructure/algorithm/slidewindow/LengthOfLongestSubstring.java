package com.disaster.infrastructure.algorithm.slidewindow;

import java.util.HashMap;

/**
 * title:
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * solution idea:
 * 滑动时间窗口，伸缩容、更新结果的时机
 *
 * @author disaster
 * @version 1.0
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {

    }

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int result = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);

            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }

            result = Math.max(result, right - left);

        }
        return result;
    }
}
