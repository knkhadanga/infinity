package main.leetcode;

/**
 * Balanced strings are those that have an equal quantity of 'L' and 'R' characters.
 * Given a balanced string s, split it in the maximum amount of balanced strings.
 * Return the maximum amount of split balanced strings.
 * <p>
 * <p>
 * Input: s = "RLRRLLRLRL"
 * Output: 4
 * Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains
 * same number of 'L' and 'R'.
 * <p>
 * Input: s = "RLLLLRRRLR"
 * Output: 3
 * Explanation: s can be split into "RL", "LLLRRR", "LR",
 * each substring contains same number of 'L' and 'R'.
 * <p>
 * <p>
 * https://leetcode.com/problems/split-a-string-in-balanced-strings/
 */
public class SplitStringBalancedString {

    public static void main(String[] args) {
        String input = "RLLLLRRRLR";
        System.out.println(balancedStringSplit(input));
    }

    static public int balancedStringSplit(String s) {
        int result = 0;
        int balanced_counter = 0;
        int length = s.length();

        char ch = s.charAt(0); //initialize with first character (R or L).
        char[] input = s.toCharArray();

        for (int i = 0; i < length; i++) {
            if (ch == input[i]) {
                balanced_counter++;
            } else {
                balanced_counter--;
            }

            if (balanced_counter == 0) {
                result++;
            }
        }

        return result;
    }

}
