package main.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * https://www.youtube.com/watch?v=yFfv03AE_vA&ab_channel=NareshGupta
 * 
 * <p>
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers
 * sum to target. You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen
 * numbers is different.
 * <p>
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] input = {2, 3, 6, 7};
        int target = 7;

        combination_sum(input, target);
        target = 9;
        combination_sum(input, target);
    }

    static void combination_sum(int[] input, int target) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(input);
        backtrack(input, target, 0, new ArrayList(), result);

        //print the result
        System.out.println("Target = " + target + " ,Result - ");
        for (List<Integer> list : result) {
            for (int x : list) {
                System.out.print(x + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    static void backtrack(int[] input, int target, int start_index, List<Integer> current_list, List<List<Integer>> result) {
        if (target < 0) { //no further computation needed, return from here.
            return;
        } else if (target == 0) { //terminal case - add curent list to final list
            result.add(new ArrayList<>(current_list));
        } else {
            for (int i = start_index; i < input.length; i++) {
                current_list.add(input[i]);//add current number to current list and recursively check for sum.
                backtrack(input, target - input[i], i, current_list, result);

                //remove the current element from current list to backtrack
                current_list.remove(current_list.size() - 1);
            }
        }
    }
}
