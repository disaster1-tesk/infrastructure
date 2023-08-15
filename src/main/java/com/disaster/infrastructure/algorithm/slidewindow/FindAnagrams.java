package com.disaster.infrastructure.algorithm.slidewindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * title:
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词: 指由相同字母重排列形成的字符串（包括相同的字符串）
 * <p>
 * Solution idea:
 * 还是滑动时间窗口算法处理，使用框架-确认扩容、缩容的时机即可
 *
 * @author disaster
 * @version 1.0
 */
public class FindAnagrams {

    public static void main(String[] args) {
        String s = "cbaebabacd",p = "abc";
        System.out.println(findAnagrams(s, p));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        char[] charArray = p.toCharArray();
        for (char c : charArray) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        ArrayList<Integer> res = new ArrayList<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c)))
                    valid++;
            }

            while (right - left >= p.length()) {
                if (need.size() == valid)
                    res.add(left);

                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return res;
    }

}
