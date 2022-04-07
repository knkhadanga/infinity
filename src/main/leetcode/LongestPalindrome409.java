package main.leetcode;

/*
Given a string s which consists of lowercase or uppercase letters, return the length of the longest
palindrome that can be built with those letters.
Letters are case sensitive, for example, "Aa" is not considered a palindrome here.

Input: s = "abccccdd"
Output: 7
Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

https://leetcode.com/problems/longest-palindrome/
https://www.youtube.com/watch?v=J_Di2LmeLBQ

*/
public class LongestPalindrome409 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
    }

    static int longestPalindrome(String input) {
        if (input == null || input.length() == 0) return 0;

        int result = 0;
        int[] count = new int[128];

        //count each character
        for (char ch : input.toCharArray()) {
            count[ch]++;
        }

        boolean odd_exist = false;

        for (int x : count) {
            if (x % 2 == 0) {
                //if the character count is even then add
                //to result as even count can be palindrome
                result += x;
            } else {
                //if its odd, then make it even and finally add 1 to the result
                result = result + (x - 1);
                odd_exist = true;
            }
        }

        if (odd_exist) {
            result = result + 1;
        }

        return result;
    }
}
