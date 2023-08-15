package com.disaster.infrastructure.huawei;

/**
 * 输入一串方波信号，求取最长的完全连续交替方波信号，并将其输出，如果有相同长度的交替方波信号，输出任一即可，方波信号高位用1标识，低位用0标识
 * <p>
 * 1) 一个完整的信号一定以0开始然后以0结尾，即010是一个完整信号，但101，1010，0101不是
 * 2）输入的一串方波信号是由一个或多个完整信号组成
 * 3）两个相邻信号之间可能有0个或多个低位，如0110010，011000010
 * 4）同一个信号中可以有连续的高位，如01110101011110001010，前14位是一个具有连续高位的信号
 * 5）完全连续交替方波是指10交替，如01010是完全连续交替方波，0110不是
 *
 * 解题思路：
 * 1) 用 00 split 信号
 * 2) 解析信息
 *
 * @author disaster
 * @version 1.0
 */
public class SquareSignal {

    public static void main(String[] args) {
        System.out.println(getMaxSquareSignal("00101010101000010100100"));
    }


    public static String getMaxSquareSignal(String signal) {
        if (signal == null || signal.isEmpty()) return "-1";
        String[] signals = signal.split("00");
        int length = signals.length;
        String result = "";
        if (length > 1) {
            for (String sign : signals) {
                if (sign.equals(""))
                    continue;
                if (sign.charAt(0)=='1')
                    sign = 0 + sign;
                if (sign.charAt(sign.length() - 1) == '1')
                    sign = sign + 0;
                String check = check(sign);
                if (!check.equals("-1") && (check.length() > result.length()))
                    result = check;
            }
        } else {
            result = check(signal);
        }
        return result;
    }

    public static String check(String signal) {
        if (!signal.contains("11") && signal.contains("01010")) {
            if (signal.matches("0+(10){2,}0*")) {
                return signal;
            }
        }
        return "-1";
    }
}
