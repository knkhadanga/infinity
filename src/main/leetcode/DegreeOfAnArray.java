package main.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
697. Degree of an Array
https://leetcode.com/problems/degree-of-an-array/

Given a non-empty array of non-negative integers nums, the degree of this array is
defined as the maximum frequency of any one of its elements.
Input: nums = [1,2,2,3,1]
Output: 2
Explanation:
    The input array has a degree of 2 because both elements 1 and 2 appear twice.
    Of the subarrays that have the same degree: [1, 2, 2, 3, 1], [1, 2, 2, 3],
    [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]

    The shortest length is 2. So return 2.
*/
public class DegreeOfAnArray {
    public static void main(String[] args) {
        int[] input = {1, 2, 2, 3, 1, 4, 2};
        System.out.println(findShortestSubArray(input));
    }

    static int findShortestSubArray(int[] nums) {

        int length = nums.length;
        if (length == 0) return 0;

        Map<Integer, Count> map = new HashMap();
        for (int i = 0; i < length; i++) {
            if (!map.containsKey(nums[i])) {
                Count c = new Count();
                c.start_index = i;
                c.end_index = i;
                c.count++;
                map.put(nums[i], c);
            } else {
                Count c = map.get(nums[i]);
                c.end_index = i;
                c.count++;
            }
        }

        int max_count = Integer.MIN_VALUE;
        int degree = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Count> entry : map.entrySet()) {
            Count temp = entry.getValue();
            int current_count = temp.count;
            int current_degree = (temp.end_index - temp.start_index) + 1;
            if (current_count > max_count) {
                max_count = current_count;
                degree = current_degree;
            } else if (current_count == max_count) {
                max_count = current_count;
                degree = Math.min(current_degree, degree);
            }
        }

        return degree;
    }

}

class Count {
    public int start_index;
    public int end_index;
    public int count;
}