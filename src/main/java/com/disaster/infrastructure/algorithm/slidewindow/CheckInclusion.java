package com.disaster.infrastructure.algorithm.slidewindow;

import java.util.HashMap;

/**
 * title:
 * 给你两个字符串s1和s2，写一个函数来判断s2是否包含s1的排列。如果是，返回true；否则，返回false
 * <p>
 * Solution idea:
 * 滑动时间窗口问题
 *
 * @author disaster
 * @version 1.0
 */
public class CheckInclusion {
    public static void main(String[] args) {
        String s  = "ab",s2 = "eidbaooo";
        System.out.println(checkInclusion(s2, s));
        String s1  = "ab",s3 = "eidboaoo";
        System.out.println(checkInclusion(s3, s1));
    }

    public static boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        char[] charArray = s2.toCharArray();
        for (char c : charArray) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        while (right < s1.length()) {
            char c = s1.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c)))
                    valid++;
            }


            while (right - left >= s2.length()) {
                // 在这里判断是否找到了合法的子串
                if (valid == need.size())
                    return true;
                char d = s1.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }

            }
        }
        return false;
    }


}
