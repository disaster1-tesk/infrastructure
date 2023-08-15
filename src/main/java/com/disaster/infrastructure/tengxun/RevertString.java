package com.disaster.infrastructure.tengxun;

public class RevertString {

    public static void main(String[] args) {
        String str = "Let's take LeetCode contest";
        System.out.println(reverseWords(str));
    }

    public static String reverseWords(String s) {
        // 拆分字符串为单词数组
        String[] words = s.split(" ");

        // 对每个单词进行字符顺序反转
        StringBuilder reversedString = new StringBuilder();
        for (String word : words) {
            StringBuilder reversedWord = new StringBuilder(word);
            reversedWord.reverse();
            reversedString.append(reversedWord).append(" ");
        }

        // 移除最后一个多余的空格
        if (reversedString.length() > 0) {
            reversedString.setLength(reversedString.length() - 1);
        }
        return reversedString.toString();
    }
}
