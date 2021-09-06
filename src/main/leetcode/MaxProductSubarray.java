package main.leetcode;

/**
 * Leetcode 152
 *
 * https://leetcode.com/problems/maximum-product-subarray/
 * <p>
 * https://www.youtube.com/watch?v=QQVCKkImH_s
 * <p>
 * Given an integer array nums, find a contiguous non-empty subarray within
 * the array that has the largest product, and return the product.
 * A subarray is a contiguous subsequence of the array.
 * <p>
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 */
public class MaxProductSubarray {

    public static void main(String[] args) {
        int[] input = {2, 3, -2, 4};
        System.out.println(maxProduct(input));
    }

    static public int maxProduct(int[] nums) {
        int length = nums.length;

        //base case
        if (length == 0) return 0;

        int current_min = nums[0];
        int current_max = nums[0];
        int result = nums[0];

        for (int i = 1; i < length; i++) {
            //a new maximum could be max of one of the following:
            // 1. product of current max and current number
            // 2. product of current min and current number (if both of them are negative)
            // 3. or the current number itself
            int temp_max = Math.max((current_max * nums[i]),
                    Math.max((current_min * nums[i]), nums[i]));

            //a new minimum could be min of one of the following:
            // 1. product of current max and current number
            // 2. product of current min and current number (if one of them is negative)
            // 3. or the current number itself
            current_min = Math.min((current_max * nums[i]),
                    Math.min((current_min * nums[i]), nums[i]));

            current_max = temp_max;

            result = Math.max(result, current_max);
        }

        return result;
    }


}
