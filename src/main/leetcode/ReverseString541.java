package main.leetcode;

/**
 * https://leetcode.com/problems/reverse-string-ii/
 *
 * Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
 *
 * If there are fewer than k characters left, reverse all of them.
 * If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and left the other as original.
 *
 * Example 1:
 *
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 */
public class ReverseString541 {
    public static void main(String[] args) {
        System.out.println(reverseStr("hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl", 39));
    }

    static public String reverseStr(String s, int k) {
        if (s == null) return s;

        int length = s.length();
        char[] input = s.toCharArray();
        if (length <= k) {
            k = length;
            helper(input, 0, length - 1);
            return new String(input);
        }

        int start_from = 0;

        while (start_from + k < length) {
            helper(input, start_from, (start_from + k) - 1);
            start_from = start_from + (2 * k);
        }

        if (start_from < length) {
            helper(input, start_from, length - 1);
        }

        return new String(input);
    }

    static void helper(char[] input, int start_index, int end_index) {
        while (start_index < end_index) {
            char x = input[start_index];
            input[start_index] = input[end_index];
            input[end_index] = x;
            start_index++;
            end_index--;
        }
    }
}
