package com.disaster.infrastructure.algorithm.slidewindow;

import java.util.HashMap;

/**
 * title:
 * 给你一个字符串s、一个字符串t。返回s中涵盖t所有字符的最小字串。如果s中不存在涵盖t所有自负的字串，则返回空字符串“”
 * <p>
 * Solution idea：
 * 采用滑动时间窗口算法进行处理（本质是快慢指针）
 *
 *
 * Frame:
 *  void slidingWindow(String s) {
 *      HashMap<Character, Integer> window = new HashMap<>();
 *
 *      int left = 0, right = 0;
 *      while (right < s.length()) {
 *          char c = s.charAt(right);
 *          window.put(c, window.getOrDefault(c, 0) + 1);
 *          right++;
 *
 *          ....
 *
 *          while (left < right && window needs shrink) {
 *              char d = s.charAt(left);
 *              window.put(d, window.get(d) - 1);
 *              left++;
 *              ...
 *          }
 *       }
 *    }
 *
 * @author disaster
 * @version 1.0
 */
public class MinCoverString {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        char[] charArray = t.toCharArray();
        // put t's character on hashmap
        for (char c : charArray) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c)))
                    valid++;
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ?
                "" : s.substring(start, start + len);

    }
}
