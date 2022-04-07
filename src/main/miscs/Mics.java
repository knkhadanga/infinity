package main.miscs;


import java.util.*;
import java.util.Stack;

/**
 * Class with misc
 */
public class Mics {

    public static void main(String[] args) {


        //permutationOfString();
        //lengthOfLongestSubstring("abcdabcdef");
        // boolean[][] SubstringLength("abccbaxxx");
        //longestPallindromeSubstring("xxxxxxxabccb");
        //longestPalindromeSubstring("yabccbax");

        //int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        //int[][] matrix = {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        //printMaxtrixInSpiralOrder(matrix);

        int[] product = {1, 2, 4, 5};
        //productExceptSelf(product);
        //findMissingNumber(product);

        //longestValidParanthesis("()()))))))");
        //removeRepetitiveString("aabcdexxxmm");

        //lengthOfLongestSubstring("kabcabcc");
        //int[] input = {1, 2, 1, 4, 5, 3, 2, 3, 7, 9, 4};
        //findDuplicatesInArray(input);
        //MaximumPossibleValue(-19);

        int[] sum = {1,-3,2,5,8};
        //subArrayWithMaxSum(sum);

        //int[] input = {1, 2, 1, 2, 5, 5, 3, 3, 4};
        //elementAppearOnlyOnce(input);

        //aToI("-1450");

        //printSubsequenceOfArray();

        //int[] input = {1, 2, 3};
        //printAllSubsets(input);

        //longestPalindromeSubstringx("a");
        //maxProfit(null);

        /*
        int[] input = {9,9,9};
        int[] output = plusOne(input);
        for (int x: output){
            System.out.print(x + " ");
        }
        */
        //pushZeroToEnd();

        //rotateMatrix();

        // generateParenthesis(3);

        //findMinimumInRotatedSortedArray();

        //findNumber();

        //maxPrimeFactor();

        //pythagorasTripletWithSum1000();

        //subarrayWithGivenSum();

        //threeSum();

        //int[] array = { 2, 0, 6, 1, 5, 3, 7 };
        //findLongestSubseqence(array);

        // int a[] = {-5, -1, -1, 2, -2, -3};
        // longestAlternatingSubarray(a);

        //int[] array = {5,6,8,2,4,6,9};
        //findDuplicatesInARange(array, 4);

        // int[] array = {10, 4, 6, 3, 5};
        // findElements(array);

        //int array[] = {33,55,13,46,87,42,10,34,43,56};
        //findFirstFiveLargest(array);

        //int[] array = {1,3,5,4,7};
        //continuousIncreasingSubsequene(array);

        //fibo();

        //findPeakElement();

        //findDuplicatesInArrayII();

        //int x = mySqrt(2020022013);
        //System.out.println(x);

        //System.out.println(judgeSquareSum(18));

        //int[] nums = {2,0,2,1,1,0};
        //sortColors(nums);

        //firstNonRepeatingCharacterInString("aabcbsde");

        //checkIfIsomorphic("paper", "title");

        int[] array = {3, 1, 4, 1, 5};
       // findPairs(array, 2);

        //System.out.println(is_anagram("sad", "das"));

        int[] nums = {23,2,4,6,7};
        System.out.println(checkSubarraySum(nums, 6));

    }

    /*
    https://www.youtube.com/watch?v=OKcrLfR-8mE
    https://leetcode.com/problems/continuous-subarray-sum/

    Given an integer array nums and an integer k, return true if nums has a continuous subarray
    of size at least two whose elements sum up to a multiple of k, or false otherwise.

    An integer x is a multiple of k if there exists
    an integer n such that x = n * k. 0 is always a multiple of k.

    Example 1:
    Input: nums = [23,2,4,6,7], k = 6
    Output: true
    Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

    Example 2:
    Input: nums = [23,2,6,4,7], k = 6
    Output: true
    Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
    And 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
     */
    static boolean checkSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>(); //to store reminder and index mapping
        map.put(0, -1); //this will ensure, if first item is multiple of k, then this will take care of it

        int total = 0;
        for (int current_index = 0; current_index < nums.length; current_index++) {
            total += nums[current_index];
            int reminder = total % k;

            if (!map.containsKey(reminder)) {
                map.put(reminder, current_index);
            } else {
                int last_index = map.get(reminder);
                if ((current_index - last_index) > 1) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
    Given an unsorted array of integers, find the length of longest increasing subsequence.
    Input: [10,9,2,5,3,7,101,18] Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

    https://www.youtube.com/watch?time_continue=135&v=Ns4LCeeOFS4
    https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
    https://leetcode.com/problems/longest-increasing-subsequence/submissions/
     */
    static void longestIncreasingSubsequence(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("Length of continuous subseqence is 0");
            return;
        }

        int max_length = 0;
        int size = array.length;

        //declare an array to hold the longest subsequence so far for each each element in array
        int[] dp = new int[size];

        //fill it with 1 as each element at least will appear once (minimum occurance for subsequence)
        Arrays.fill(dp, 1);

        //now compare each element in array with all the previous element which are
        //less to it to calculate the longest subsequence
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) { //start from 0 and compare till current element
                if (array[i] > array[j]) {
                    //the max length of subsequence for current element could be current value
                    //or it is 1 more than the element which is being compared (array[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //update the max length value
            max_length = Math.max(dp[i], max_length);
        }

        System.out.println("Longest increasing subsequence length is " + max_length);
    }

    /*
    Given an unsorted array of integers, find the length of longest
    continuous increasing subsequence (subarray).

    Input: [1,3,5,4,7] Output: 3
    Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
    Even though [1,3,5,7] is also an increasing subsequence but it's not a continuous
    as 5 and 7 are separated by 4.
    https://leetcode.com/problems/longest-continuous-increasing-subsequence/
     */
    static void continuousIncreasingSubsequene(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("Length of continuous subseqence is 0");
            return;
        }

        int temp_length = 1; //at least one element is continuous
        int max_length = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                temp_length++;
            } else {
                max_length = Math.max(temp_length, max_length);
                temp_length = 1;
            }
        }

        max_length = Math.max(temp_length, max_length);
        System.out.println("Length of longest continuous increasing subsequence is - " + max_length);
    }

    /*
    Given a string, reverse the letters in each word and leave the rest of the string as is.
    A word is any uninterrupted sequence of Latin letters (\[A-Za-z]+).
    Anything else is a word separator.

    "abc123def" becomes "cba123fed"
    "Hello world, this is me." becomes "olleH dlrow, siht si em."
    "12ab34" becomes "12ba34".
     */
    void reverseString(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Invalid input");
            return;
        }

        StringBuilder stb = new StringBuilder();
        String output = "";
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                stb.append(ch);
            } else {
                output = output + stb.reverse().toString();
                output = output + ch;
                stb.setLength(0);
            }
        }

        if (stb.length() != 0) {
            output = output + stb.reverse().toString();
        }

        System.out.println("Output - " + output);
    }

    /*
    Given an input integer, insert 5 at a position so that the
    output value will have maximum value.

    e.g.: For input 101, 5 can be inserted at index 0 so that
    the value can be 5101 which is a maximum number.
     */
    static public void MaximumPossibleValue(int N) {
        StringBuilder stb = new StringBuilder(String.valueOf(Math.abs(N)));
        int sign = (N > 0) ? 1 : -1;
        int length = stb.length();

        if (sign > 0) {
            int index = 0;
            while (index < length && ((stb.charAt(index) - '0') > 5)) {
                index++;
            }
            stb.insert(index, '5');
        } else {
            int index = 0;
            while (index < length && ((stb.charAt(index) - '0') < 5)) {
                index++;
            }
            stb.insert(index, '5');
        }

        int value = Integer.parseInt(stb.toString());
        value = value * sign;
        System.out.println("Value - " + value);
    }

    /*
    Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
    some elements appear twice and others appear once. Find all the elements of [1, n]
    inclusive that do not appear in this array.

    Input: [4,3,2,7,8,2,3,1] Output: [5,6]
    https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
    https://www.youtube.com/watch?v=N6uiqEbF0o0
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> output = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return output;
        }

        for (int i = 0; i < nums.length; i++) {
            int element = Math.abs(nums[i]);
            //since element value in array can be 1 to N and array size is N.
            //so the index size can be 0 to N-1
            int index = element - 1;

            //get the element and mark the element and make the element in that index to negative
            if (nums[index] > 0) { //if not negative, make it negative
                nums[index] = nums[index] * -1;
            }
        }

        //if the element in array is positive at a given index,
        // it means the (index + 1) is missing from array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                output.add(i + 1);
            }
        }

        return output;
    }

    /*
    Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
    some elements appear twice and others appear once.
    Find all the elements that appear twice in this array.
    Input: [4,3,2,7,8,2,3,1] Output: [2,3]
    https://leetcode.com/problems/find-all-duplicates-in-an-array/

    https://www.youtube.com/watch?v=aMsSF1Il3IY
     */
    static void findDuplicatesInArray(int[] input) {
        if (input == null || input.length == 0) {
            System.out.println("Invalid input");
            return;
        }

        List<Integer> outputList = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {

            int current_value = Math.abs(input[i]);//make it positive if it is negative

            //index can have range from 0 to n-1 in an array.
            int current_index = current_value - 1;

            if (input[current_index] < 0) {
                //value is already negative, it means the data at this index has
                //already been modified before. This index is duplicate
                outputList.add(current_value);
            } else {
                //make the value at index negative if it is positive
                input[current_index] = -1 * input[current_index];
            }
        }

        if (outputList.size() == 0) {
            System.out.println("No repeating elements found in array");
            return;
        } else {
            System.out.println("Repeating elements are:");
            for (int x : outputList) {
                System.out.print(x + " ");
            }
        }
    }

    /*
    Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
    prove that at least one duplicate number must exist.
    Assume that there is only one duplicate number, find the duplicate one.

    1. You must not modify the array (assume the array is read only).
    2. You must use only constant, O(1) extra space.
    3. Your runtime complexity should be less than O(n2).
    4. There is only one duplicate number in the array, but it could be repeated more than once.
    https://leetcode.com/problems/find-the-duplicate-number/
    https://medium.com/solvingalgo/solving-algorithmic-problems-find-a-duplicate-in-an-array-3d9edad5ad41
     */
    static void findDuplicatesInArrayII() {
        int[] input = {1, 3, 4, 2, 2};
        int slow = input[0];
        int fast = input[input[0]];

        //to detect a cycle in the array
        while (input[slow] != input[fast]) {
            slow = input[slow];
            fast = input[input[fast]];
        }

        //now detect the point of loop
        slow = 0; //set it to first index
        while (slow != fast) {
            slow = input[slow];
            fast = input[fast];
        }

        System.out.println("Duplicate number is - " + slow);
    }

    /**
     *
     *  find the sum of contiguous subarray within a one-dimensional
     *  array of numbers which has the largest sum.
     * Efficient apporach
     * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
     *
     * @param array
     */
    static void subArrayWithMaxSum(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("array is empty .. sum is zero");
            return;
        }

        int current_sum = 0;
        int max_sum = Integer.MIN_VALUE;

        for (int item : array) {
            current_sum = current_sum + item;
            max_sum = Math.max(max_sum, current_sum);

            if (current_sum < 0) {
                current_sum = 0;
            }
        }
        System.out.println("Max sum - " + max_sum);
    }

    /*
    Write a program to print all permutations of a given string.
    Below are the permutations of string ABC.
        [ABC ACB BAC BCA CBA CAB]
     */
    static void permutationOfString() {
        String input = "ABC";
        if (input == null || input.isEmpty()) {
            System.out.println("Invalid input..");
            return;
        }

        int length = input.length();
        permutationOfString(input, 0, length - 1);
    }

    static void permutationOfString(String input, int leftIndex, int rightIndex) {

        if (leftIndex > rightIndex) {
            System.out.println(input + " ");
        } else {
            for (int index = leftIndex; index <= rightIndex; index++) {
                input = swap(input, leftIndex, index);
                permutationOfString(input, leftIndex + 1, rightIndex);
                //input = swap (input, leftIndex, index);
            }
        }
    }

    static String swap(String input, int left, int right) {
        char[] array = input.toCharArray();
        char x = array[left];
        array[left] = array[right];
        array[right] = x;

        return new String(array);
    }

    /**
     * Given a string, find the length of the longest substring without repeating
     * characters. Input: "abcabcbb" Output: 3 Explanation: The answer is "abc",
     * with the length of 3.
     * <p>
     * efficient approach
     *
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     */
    public static void lengthOfLongestSubstring(String input, String dummy) {
        if (input == null || input.length() == 0) {
            return;
        }

        int str_length = input.length();
        Set<Character> set = new HashSet<>();

        int left_index = 0; //pointing to first char in the string
        int current_index = 0;  //pointing to first char in the string
        int longest = 0;

        while (current_index < str_length) {
            char ch = input.charAt(current_index);
            if (!set.contains(ch)) {
                set.add(ch);
                current_index++;
                longest = Math.max(longest, set.size());
            } else {
                //remove all characters from set until the duplicate char is found
                set.remove(input.charAt(left_index));
                left_index++;
            }
        }

        System.out.println("longest substring without repeating characters - " + longest);
    }

    /**
     * Input: abccccdeeef Output: abdf
     * Question - https://www.youtube.com/watch?v=yJv_ltADGuA
     */
    static void removeRepetitiveString(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Invalid input..");
            return;
        }

        StringBuilder stb = new StringBuilder();
        int length = input.length();
        int i = 0;
        char[] array = input.toCharArray();

        while (i < length) {
            if ((i + 1 < length) && (array[i] != array[i + 1])) {
                stb.append(array[i]);
                i++;
            }

            if ((i + 1) < length && (array[i] == array[i + 1])) {
                while ((i + 1) < length && (array[i] == array[i + 1])) {
                    i++;
                }

                i++;
            }

            if (i == length - 1) {
                stb.append(array[i]);
                break;
            }
        }

        System.out.println(stb.toString());
    }

    /**
     * This method used for finding length of
     * longest valid parenthesis in a given input.
     *
     * @param input
     */
    static void longestValidParanthesis(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Invalid input..");
            return;
        }

        int length = input.length();
        int result = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(-1); //base case

        for (int currentIndex = 0; currentIndex < length; currentIndex++) {
            char ch = input.charAt(currentIndex);
            if (ch == '(') {
                stack.push(currentIndex);
            } else {
                stack.pop(); //pop the top element
                if (stack.isEmpty()) {
                    stack.push(currentIndex);
                } else {
                    result = Math.max(result, currentIndex - stack.peek());
                }
            }
        }

        System.out.println("Logest valid paranthesis length - " + result);
    }

    /**
     * Find the missing number in an array
     *
     * @param input
     */
    static void findMissingNumber(int[] input) {
        if (input == null || input.length == 0) {
            System.out.println("Invalid output");
            return;
        }

        int size = input.length;
        int originalXOR = input[0];
        for (int x = 1; x < size; x++) {
            originalXOR = originalXOR ^ input[x];
        }

        int tempXOR = 1;
        for (int i = 2; i <= size + 1; i++) {
            tempXOR = tempXOR ^ i;
        }

        System.out.println(originalXOR + " " + tempXOR);

        System.out.println("Missing no = " + (originalXOR ^ tempXOR));

    }

    /**
     * Method to print a matrix in spiral order
     *
     * @param matrix
     */
    static void printMaxtrixInSpiralOrder(int[][] matrix) {
        if (matrix == null) {
            System.out.println("Invalid input array");
            return;
        }

        int noOfrows = matrix.length;
        int noOfColumn = matrix[0].length;

        int top = 0; // first row
        int bottom = noOfrows - 1; //last row
        int left = 0; //first column
        int right = noOfColumn - 1; // last column
        int direction = 0;

        while (top <= bottom && left <= right) {

            if (direction == 0) {
                for (int x = top; x <= right; x++) {
                    System.out.print(matrix[top][x] + " ");
                }
                top++;
            } else if (direction == 1) {
                for (int x = top; x <= bottom; x++) {
                    System.out.print(matrix[x][right] + " ");
                }
                right--;
            } else if (direction == 2) {
                for (int x = right; x >= left; x--) {
                    System.out.print(matrix[bottom][x] + " ");
                }
                bottom--;
            } else if (direction == 3) {
                for (int x = bottom; x >= top; x--) {
                    System.out.print(matrix[x][left] + " ");
                    left++;
                }
            }

            direction = (direction + 1) % 4;
        }
    }



    static boolean isPallindrome(StringBuilder stb) {
        String x = stb.toString();
        String y = new StringBuilder(x).reverse().toString();

        return x.equals(y);
    }

    static void productExceptSelf(int[] input) {
        if (input == null || input.length == 0) {
            System.out.println("Invalid input");
            return;
        }

        int length = input.length;
        int[] output = new int[length];
        Arrays.fill(output, 1);


        /*
        //brute force approach
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != j) {
                    output[i] = output[i] * arr[j];
                }
            }
        }
        */

        //store product of all left elements of a given index
        int temp = 1;
        for (int i = 0; i < length; i++) {
            output[i] = temp;
            temp = temp * input[i];
        }

        //store product of all right elements of a given index
        temp = 1;
        for (int i = length - 1; i >= 0; i--) {
            output[i] = temp * output[i];
            temp = temp * input[i];
        }

        for (int x : output) {
            System.out.print(" " + x);
        }
    }

    /*    Write an efficient algorithm that searches for a value in an m x n matrix.
    This matrix has the following properties:

    1. Integers in each row are sorted from left to right.
    2. The first integer of each row is greater than the last integer of the previous row.

    Input:
    matrix = [
        [1,   3,  5,  7],
        [10, 11, 16, 20],
        [23, 30, 34, 50]
      ]
    target = 3
    Output: true
 */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int noOfRows = matrix.length;
        int noOfColumns = matrix[0].length;

        if (noOfRows == 0 || noOfColumns == 0) {
            return false;
        }

        int rowIndex = 0;
        int lastColumn = noOfColumns - 1;

        if (matrix[noOfRows - 1][noOfColumns - 1] < target) {
            // System.out.println("target does not exists.");
            return false;
        }

        while (rowIndex < noOfRows) {
            if (matrix[rowIndex][lastColumn] >= target) {
                break;
            }
            rowIndex++;
        }

        for (int i = 0; i < noOfColumns; i++) {
            if (matrix[rowIndex][i] == target) {
                // System.out.println("Index where target found - " + rowIndex +"," + i);
                return true;
            }
        }

        return false;
    }

    /*
    Given an array of integers, all the elements are appear twice
    but one element which appears only once.
    Write an algorithm to find that element.
    https://algorithms.tutorialhorizon.com/find-the-only-element-in-array-which-appears-only-once/
     */

    static void elementAppearOnlyOnce(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("Invalid array");
            return;
        }

        //upon xoring, elements appeard twice will cancel each other
        //only non repeating element will remain.
        int xor = array[0];
        for (int i = 1; i < array.length; i++) {
            xor = array[i] ^ xor;
        }

        System.out.println("Missing element - " + xor);
    }

    /**
     * Method to convert given integer in string format to integer.
     * Example: "134" will return 134 (integer)
     * Example: "-238" will return -238 (integer)
     *
     * @param input in string format
     */
    static void aToI(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("invalid input string");
            return;
        }

        char[] array = input.toCharArray();

        //check for the sign
        char ch = input.charAt(0);
        int sign = (ch == '-') ? -1 : 1;

        //if the number is negetive, then ignore the first character
        if (sign == -1) {
            //substring from start index 1 to end
            input = input.substring(1);
        }

        int total = 0;
        for (int i = 0; i < input.length(); i++) {
            //get the numeric value of character
            ch = input.charAt(i);
            int digit = ch - '0';

            //check if the character is digit or not (value should be 0-9)
            if (digit < 0 || digit > 9) {
                System.out.println("Invalid input.");
                return;
            }

            //check for the boundary condition.
            //if the current sum is more than max_value/10 then it will cross boundary on multiplying by 10.
            //if the current digit is greater than Integer.MAX_VALUE % 10, then upon multiplying by 10 and adding
            //digit will cross the boundary again.
            if (total >= Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10) {
                if (sign == -1) {
                    System.out.println("Converted value = " + Integer.MIN_VALUE);
                } else {
                    System.out.println("Converted value = " + Integer.MAX_VALUE);
                }
                return;
            }

            total = (total * 10) + digit;

        }

        System.out.println("Converted value = " + (total * sign));
    }

    /*
    Given a sorted array nums, remove the duplicates in-place such that
    each element appear only once and return the new length.

    Given nums = [0,0,1,1,1,2,2,3,3,4],
    Your function should return length = 5, with the first five elements of nums
    being modified to 0, 1, 2, 3, and 4 respectively.

    https://leetcode.com/problems/remove-duplicates-from-sorted-array/
     */
    static void removeDuplicates(int[] array) {
        if (array.length == 0 || array.length == 1) {
            System.out.println("0");
            return;
        }

        int index = 0;
        for (int j = 1; j < array.length; j++) {
            if (array[index] != array[j]) {
                index++;
                array[index] = array[j];
            }
        }

        System.out.println("Length of array = " + (index + 1));
    }

    /*
   Given a set of distinct integers, nums, return all possible subsets (the power set).
   Note: The solution set must not contain duplicate subsets.

   Subset - In the subset, the items can be in a non contiguous
   way from original array

   Input: nums = [1,2,3]
   Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
   https://leetcode.com/problems/subsets/
    */
    static void printAllSubsets(int[] array) {
        if (array.length == 0) {
            System.out.println("nothing to print");
            return;
        }

        System.out.println("\n\nPrinting sub subset of array");
        List<List<Integer>> output = new ArrayList<>();

        //initialize with empty value
        output.add(new ArrayList<>());

        for (int i = 0; i < array.length; i++) {
            //get the current size of output to find
            //the number of subset exists so far
            int size = output.size();
            for (int j = 0; j < size; j++) {
                //iterate through the output list to get all the subsets
                List<Integer> subset = output.get(j);

                //make a copy of it and add new item to the list
                List<Integer> newSubset = new ArrayList<>(subset);
                newSubset.add(array[i]);

                //add the new list the final result
                output.add(newSubset);
            }
        }

        //print the final result
        for (List<Integer> subset : output) {
            System.out.print(subset);
        }
    }


    /*
    Given an array write an algorithm to print all the possible sub subsequences.
    int [] a = {1, 2, 3};
    Output: Possible sub sequences – {Empty}, {1}, {2}, {3}, {1, 2} ,{1,3}, {2, 3}, {1, 2, 3}
     */
    static void printSubsequenceOfArray() {
        int[] array = {1, 2, 3};
        System.out.println("\nPrinting sub sequence of array");
        if (array == null || array.length == 0) {
            System.out.println("invalid array ..");
            return;
        }

        //pick starting point
        for (int i = 0; i < array.length; i++) {
            //pick end point
            for (int j = i; j < array.length; j++) {
                //print sub array
                System.out.print("[");
                for (int k = i; k <= j; k++) {
                    System.out.print(array[k] + " ");
                }
                System.out.print("]");
            }
        }
    }


    /*
    Say you have an array for which the ith element is the price of a given stock on day i.
    If you were only permitted to complete at most one transaction
    (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
    Note that you cannot sell a stock before you buy one.
    Input: [7,1,5,3,6,4] Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Not 7-1 = 6, as selling price needs to be larger than buying price.
     */
    static public int maxProfitInStock() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int current_minimum = Integer.MAX_VALUE;
        int max_profit = 0;

        for (int current_price : prices) {
            current_minimum = Math.min(current_minimum, current_price);
            int current_profit = current_price - current_minimum;
            max_profit = Math.max(max_profit, current_profit);
        }

        System.out.println("Max profit = " + max_profit);
        return max_profit;
    }


    /*
    Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
    The digits are stored such that the most significant digit is at the head of the list,
    and each element in the array contain a single digit. You may assume the integer does not contain
    any leading zero, except the number 0 itself.

    Input: [1,2,3]
    Output: [1,2,4]
    Explanation: The array represents the integer 123.

    Input: [4,3,2,1]
    Output: [4,3,2,2]
    Explanation: The array represents the integer 4321.
     */
    static int[] plusOne(int[] input) {
        int length = input.length;

        for (int i = length - 1; i >= 0; i--) {
            //if the number is less than 9, then increment it and return
            if (input[i] < 9) {
                input[i] = input[i] + 1;
                return input;
            }

            //otherwise set it to zero
            input[i] = 0;
        }

        //if the number is like 999, then the first digit should be 1
        int[] result = new int[length + 1];

        //set the first digit to 1 .. rest all digits are already set to 0 by default
        result[0] = 1;

        return result;
    }

    /*
    Given an array nums, write a function to move all 0's to the end of it
    while maintaining the relative order of the non-zero elements.
    Example:
    Input: [0,1,0,3,12]
    Output: [1,3,12,0,0]
     */
    static void pushZeroToEnd() {
        int[] array = {0, 1, 0, 3, 12};
        int length = array.length;

        int count = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] != 0) {
                array[count] = array[i];
                count++;
            }
        }

        while (count < length) {
            array[count] = 0;
            count++;
        }

        for (int x : array) {
            System.out.print(x + " ");
        }
    }

    /*
    You are given an n x n 2D matrix representing an image.
    Rotate the image by 90 degrees (clockwise).

    Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],
,
rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
     */
    static void rotateMatrix() {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int size = array.length;
        int top = 0;
        int bottom = size - 1;
        /*  1,2,3
            4,5,6
            7,8,9
        */
        //reverse the row order
        while (top < bottom) {
            int[] temp = array[top];
            array[top] = array[bottom];
            array[bottom] = temp;

            top++;
            bottom--;
        }

         /*  7,8,9
            4,5,6
            1,2,3
        */

        //transpose the matrix
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                int temp = array[i][j];
                array[i][j] = array[j][i];
                array[j][i] = temp;
            }
        }
        /*  7,4,1
            8,5,2
            9,6,3
        */

        //print it
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /*
        Given n pairs of parentheses, write a function to generate all combinations
        of well-formed parentheses. For example, given n = 3, a solution set is:

        "((()))", "(()())", "(())()", "()(())", "()()()"
        https://algorithms.tutorialhorizon.com/generate-all-valid-parenthesis-strings-of-length-2n-of-given-n/
     */
    static void generateParenthesis(int n) {
        if (n == 0) {
            return;
        }

        if (n == 1) {
            System.out.println("()");
            return;
        }

        generateParenthesis(n, n, "");
    }

    static void generateParenthesis(int leftParenthesis, int rightParenthesis, String output) {
        //if there is no paranthesis left, then print it
        if (leftParenthesis == 0 && rightParenthesis == 0) {
            System.out.println(output);
        }

        //if the number of left parenthesis is more than the right paranthesis
        //then it is a invalid condition. At any point, The number of right paranthesis should
        //always be more than or equal to the number of left paranthesis,
        // otherwise the parenthesis cannot be well formed
        if (leftParenthesis > rightParenthesis) {
            //return from here as this is not a valid scenario to be considered
            return;
        }

        if (leftParenthesis > 0) {
            //recursively call to form all valid expressions by adding a left paranthesis to output
            //and decrement the count of left paranthesis
            generateParenthesis(leftParenthesis - 1, rightParenthesis, output + "(");
        }

        if (rightParenthesis > 0) {
            //recursively call to form all valid expressions by adding a right paranthesis to output
            //and decrement the count of right paranthesis
            generateParenthesis(leftParenthesis, rightParenthesis - 1, output + ")");
        }

    }

    /*
        Suppose a sorted array is rotated at some pivot unknown to you beforehand.
        (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). Find the minimum element.
        You may assume no duplicate exists in the array.

        This can be done by linear search of array. However, it is not efficient.
        Here is the binary search mechanism.
     */
    static int findMinimumInRotatedSortedArray() {
        int[] array = {7, 6, 5, 4, 0, 1, 2, 3};
        if (array == null || array.length == 0) {
            return -1;
        }

        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            int middle = start + (end - start) / 2;

            if ((middle > 0) && (array[middle] < array[middle - 1])) {
                return array[middle];
            }

            if ((array[start] <= array[middle]) && (array[middle] > array[end])) {
                //left half is sorted .. search in right half
                start = middle + 1;
            } else {
                //right half is sorted .. search in left half
                end = middle;
            }
        }

        //if not roatated, then return the start item
        return array[start];
    }

    /*
    2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
    What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
     */

    static void findNumber() {
        int i = 2520;
        boolean found = false;

        while (!found) {
            i = i + 2520;
            boolean isDivisible = true;
            for (int a = 11; a <= 20; a++) {
                if (i % a != 0) {
                    isDivisible = false;
                    break;
                }
            }

            if (isDivisible) {
                found = true;
            }
        }

        System.out.println("Smallest number divisible by 1 - 20 is " + i);
    }

    static void maxPrimeFactor() {
        //sum of first n numbers = n(n+1)/2
        //sum of first n squares = n(n+1)(2n+1)/6

        long n = 100;
        long sumOfNumbers = n * (n + 1) / 2;
        long sumOfSquares = n * (n + 1) * (2 * n + 1) / 6;

        sumOfNumbers = sumOfNumbers * sumOfNumbers;
        sumOfSquares = sumOfSquares * sumOfSquares;

        System.out.println("Difference = " + (sumOfSquares - sumOfNumbers));
    }

    /*
    A Pythagorean triplet is a set of three natural numbers, a < b < c, for which, a^2 + b^2 = c^c
    e.g: (3*3) + (4*4) = (5*5)

    There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product a*b*c.
    https://projecteuler.net/problem=9
     */
    static void pythagorasTripletWithSum1000() {
        int sum = 1000;
        int a = 1, b = 1, c = 0;
        outer:
        for (a = 1; a <= sum / 3; a++) {
            for (b = a + 1; b <= sum / 2; b++) {
                c = sum - a - b;
                if (a * a + b * b == c * c) {
                    System.out.println("Triplet with sum 1000 are a = " + a + " b = " + b + " c = " + c);
                    break outer;
                }
            }
        }

        System.out.println("Product of triplet = " + (a * b * c));
    }


    /**
     * Given an unsorted array of non negative integers,
     * find a continuous sub array which adds to a given number.
     * <p>
     * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
     * Ouptut: Sum found between indexes 2 and 4
     * <p>
     * https://www.geeksforgeeks.org/find-subarray-with-given-sum/
     */
    static void subarrayWithGivenSum() {
        int[] arr = {1, 4, 20, 3, 10, 5, 9};
        int sum = 15;
        int size = arr.length;

        int currentSum = 0;
        int leftIndex = 0;
        for (int currentIndex = 0; currentIndex < size; currentIndex++) {
            currentSum = currentSum + arr[currentIndex];

            //If currentSum exceeds the sum, then remove trailing elements
            //while currentSum is greater than the sum.
            while (currentSum > sum && leftIndex < currentIndex) {
                currentSum = currentSum - arr[leftIndex];
                leftIndex++;
            }

            if (currentSum == sum) {
                System.out.println("Index found from " + leftIndex + " to " + currentIndex);
                return;
            }
        }

        System.out.println("Index not found.");
    }


    List<Integer> twoSum(int[] array, int sum) {

        List<Integer> output = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int size = array.length;

        for (int i = 0; i < size; i++) {
            int compliment = sum - array[i];
            if (map.containsKey(compliment)) {
                output.add(map.get(compliment));
                output.add(i);
                //return output;

                map.remove(compliment);
                continue;
            }
            map.put(array[i], i);
        }

        return output;
    }


    /*
    Given an array nums of n integers, are there elements a, b, c in nums
    such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
    Note: The solution set must not contain duplicate triplets.
    Example: Given array nums = [-1, 0, 1, 2, -1, -4],
    A solution set is:
    [
        [-1, 0, 1],
        [-1, -1, 2]
    ]
     */
    static List<List<Integer>> threeSum() {
        int[] array = {-1, 0, 1, 2, -1, -4};

        //sort the array first
        Arrays.sort(array);
        //Set to store all unique lists
        Set<List<Integer>> result = new HashSet<>();

        if (array.length == 0) {
            return Collections.EMPTY_LIST;
        }

        int size = array.length;

        //start from 1st element and iterate until n-2 as triplet has to be found
        for (int start = 0; start < size - 2; start++) {
            int left = start + 1; //next to i
            int right = size - 1; //slide from right to left

            //check for all possible combinations of elements with sum == 0
            while (left < right) {
                int sum = array[start] + array[left] + array[right];
                if (sum == 0) {
                    //add numbers to result and check for others
                    //and Set will make sure the results have no duplicates
                    result.add(Arrays.asList(array[start], array[left], array[right]));
                    left++;
                    right--;
                    continue;
                } else if (sum < 0) { //as array sorted, all small numbers are at left.. so slide it to right
                    left++;
                } else if (sum > 0) {
                    right--;
                }
            }
        }

        List<List<Integer>> output = new ArrayList<>(result);
        System.out.println(output);
        return output;
    }

    /*
    You are climbing a stair case. It takes n steps to reach to the top.
    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    Input: 3 Output: 3
    Explanation: There are three ways to climb to the top.
    1. 1 step + 1 step + 1 step
    2. 1 step + 2 steps
    3. 2 steps + 1 step
     */

    static void climbingStairs(int n) {
        //declare an array of size n + 1 to store the results for all the stairs
        //(index of array corresponds to the stair number)
        //and an extra space for no stairs use case.
        int[] results = new int[n + 1];

        //base case - If there is 0 stairs, then there is only one possibility it cannot be climbed
        results[0] = 1;

        //base case - If there is 1 stairs, then there is only one possibility it can be climbed in one way
        results[1] = 1;

        for (int i = 2; i <= n; i++) {
            results[i] = results[i - 1] + results[i - 2];
        }

        int output = results[n];
        System.out.println("Various way to climb the stairs are - " + output);
    }


    /*
   Given an array of integers, find the length of longest subsequence formed by the consecutive integers.
   The subsequence should contain all distinct values and the character set should be consecutive, irrespective of its order.

   For example, consider array { 2, 0, 6, 1, 5, 3, 7 }.
   The largest subsequence formed by the consecutive integers is { 2, 0, 1, 3 }

   Solution 1 - This can be done by sorting the array and finding the sequence (n log n complexity).
   Solution 2 - We can do better using Hashing. The idea is to consider each element of the input sequence
   and find the maximum length consecutive subsequence starting with it. i.e. for every element e, we check
   for presence of elements e+1, e+2, e+3.. in the input. We can optimize the code by using set for
   constant time lookups to determine if the element present in the input sequence or not.

   https://www.techiedelight.com/find-longest-subsequence-formed-by-consecutive-integers/
    */
    static void findLongestSubseqence(int[] array) {
        Set<Integer> set = new HashSet<>();
        //add elements to set for look up
        for (int x : array) {
            set.add(x);
        }

        int max_length = 1; //at least one element with subsequence
        //now iterate through the array to find the longest subsequence of consecutive integers
        for (int element : array) {
            // check if current element e is candidate for starting of a sequence
            // i.e. previous element (e - 1) don't exist in the set
            if (!set.contains(element - 1)) {
                //check if e+1, e+2, e+3 exists or not
                int length = 1;
                while (set.contains(element + length)) {
                    length++;
                }
                max_length = Math.max(max_length, length);
            }
        }

        System.out.println("Length of largest subsequence formed by the consecutive integers is - " + max_length);
    }

    /*
    Length of the longest alternating subarray
    Given an array of N including positive and negative numbers only.
    The task is to find the length of the longest alternating
    (means negative-positive-negative or positive-negative-positive) subarray present in the array.

    Examples:
    Input: a[] = {-5, -1, -1, 2, -2, -3}
    Output: 3
    The subarray {-1, 2, -2}

    Input: a[] = {1, -5, 1, -5}
    Output: 4
    https://www.geeksforgeeks.org/length-of-the-longest-alternating-subarray/
     */

    static void longestAlternatingSubarray(int[] array) {
        int length = array.length;
        int longest = 0;
        int count = 1;
        for (int i = 1; i < length; i++) {
            if (array[i] * array[i - 1] < 0) {
                count++;
                longest = Math.max(count, longest);
            } else {
                count = 1;
            }
        }

        System.out.println("Longest alternating subarray = " + longest);
    }

    /*
    Given an array and a positive number k, check weather the array contains any duplicate elements within range k.
    If k is more than size of the array, the solution should check for duplicates in the complete array.

    Solution - Sliding window technique
    The idea is process every window of size k one at a time and store its elements in a set.
    Now if any element repeats in the window, then we can say that it is repeated within range of k.
    Initially the window will contain the first k elements of the input. Then for each element of the remaining input,
    we add it to the current window. While adding the i’th element of the input to the current window,
    we have to remove the (i-k)’th element from the window at the same time.
    This is done to maintain efficiency of the solution and to keep window balance at any point.

    https://www.techiedelight.com/find-duplicates-within-given-range-array/
     */
    static void findDuplicatesInARange(int[] array, int k) {
        //set to contain all the elements in a range
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < array.length; i++) {

            if (set.contains(array[i])) {
                System.out.println("Duplicates found in a range.");
                return;
            }

            set.add(array[i]);

            if (i >= k) {
                set.remove(array[i - k]);
            }
        }

        System.out.println("No duplicates found in a range.");
    }


    /*
    Given an unsorted array of integers, print all elements which are greater than all elements present to its right.
    Example: Elements greater than all elements to its right for array {10, 4, 6, 3, 5} are 10, 6, and 5"

    Solution - For each element, we pop all the elements present in the stack that are less than it and then push it
    into the stack. Finally, the stack is left with the elements which are greater than all elements present to its right.
    https://www.techiedelight.com/find-elements-array-greater-than-elements-right/
     */

    static void findElements(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("Invalid input, No elements found..");
            return;
        }

        Stack<Integer> stack = new Stack<>();

        //traverse the array from left to right
        for (int x : array) {
            //pop all the elements from stack that are less than current element
            while (!stack.isEmpty() && x > stack.peek()) {
                stack.pop();
            }

            stack.push(x);
        }

        if (stack.isEmpty()) {
            System.out.println("No element found in the array.");
        } else {
            System.out.println("Elements found are - \n");
            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
        }
    }

    /*
    Largest 5 in an array without sorting
    https://stackoverflow.com/questions/32395648/largest-5-in-array-of-10-numbers-without-sorting
     */
    static void findFirstFiveLargest(int[] array) {
        int[] output = new int[5];

        for (int i = 0; i < 5; i++) { //loop to detect the 5 max element
            int max = array[0]; //set the first element to max
            int index = 0;
            for (int j = 1; j < array.length; j++) { //scanning the array to find the max element
                if (array[j] > max) {
                    max = array[j];
                    index = j;
                }
            }

            output[i] = max;
            array[index] = Integer.MIN_VALUE; //set the value to minimum to find next max element
        }

        System.out.println("First five maximum numbers in array:");
        for (int x : output) {
            System.out.print(x + " ");
        }
    }

    static void fibo() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        System.out.println(fib(10, map));
    }

    static int fib(int n, Map<Integer, Integer> map) {
        int x, y;
        if (map.containsKey(n - 1)) {
            System.out.println("Got n-1 from map " + (n - 1));
            x = map.get(n - 1);
        } else {
            System.out.println("Got n-1 from call " + (n - 1));
            x = fib(n - 1, map);
            map.put(n - 1, x);
        }

        if (map.containsKey(n - 2)) {
            y = map.get(n - 2);
            System.out.println("Got n-2 from map " + (n - 2));
        } else {
            System.out.println("Got n-2 from call " + (n - 2));
            y = fib(n - 2, map);
            map.put(n - 2, y);
        }

        return (x + y);
    }

    /*
    A peak element is an element that is greater than its neighbors.
    Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
    The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
    You may imagine that nums[-1] = nums[n] = -∞.

    Input: nums = [1,2,3,1]  Output: 2
    Explanation: 3 is a peak element and your function should return the index number 2.

    Input: nums = [1,2,3,4] Output: 3
    Explanation: 4 is a peak element and your function should return the index number 3.

    Your solution should be in logarithmic complexity.

    https://leetcode.com/problems/find-peak-element/
    https://www.youtube.com/watch?v=CFgUQUL7j_c

     */

    static void findPeakElement() {
        int[] array = {1, 2, 12, 3, 4};
        int size = array.length;

        //solution-1 with O(n)
        /*
        for (int i = 1; i < size - 1; i++){
            if (array[i-1] > array[i]){
                System.out.println("Peak element index is - " + (i-1));
                return;
            }
        }
        System.out.println("Peak element index is - " + (size - 1));
        */
        //solution-2 with O(log N)
        //use binary search to find the index. The array is sorted till the peak index
        //so we can use binary search to find the element which is peak index
        int left = 0;
        int right = size - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[mid + 1]) {
                //left half is sorted .. so peak element will be in right half
                left = mid + 1;
            } else {
                //right half is sorted .. so peak element will be in left half
                right = mid;
            }
        }

        System.out.println("Peak element index is - " + left);
    }

    /*
    Implement int sqrt(int x).
    Compute and return the square root of x, where x is guaranteed to be positive integer.
    Since the return type is an integer, the decimal digits are truncated and only the integer part
    of the result is returned.

    Input: 4 Output: 2

    Input: 8 Output: 2
    Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.

    https://leetcode.com/problems/sqrtx/
     */
    static int mySqrt(int x) {
        long left = 1;
        long right = x;
        long res = 0;

        while (left <= right) {
            //use binary search method
            long mid = left + (right - left) / 2;

            if ((mid * mid) == x) {
                return (int) mid;
            } else if ((mid * mid) > x) {
                right = mid - 1; //search in left half
            } else {
                //assign result to mid .. this could be the closest to square root
                // before searching in right half
                res = mid;
                left = mid + 1;
            }
        }

        return (int) res;
    }

    /*
    Implement pow(x, n), which calculates x raised to the power n (xn).

    Input: 2.00000, 10 Output: 1024.00000

    Input: 2.00000, -2 Output: 0.25000
    Explanation: 2-2 = 1/22 = 1/4 = 0.25
    https://leetcode.com/problems/powx-n/

     */
    double powerOfX(double x, int n) {
        //first approach
        /*
        double result = 1;
        if (n < 0)
            x = 1 / x;

        while (n != 0) {
            if (n % 2 != 0)
                result = result * x;
            x = x * x;
            n = n / 2;
        }
        return result;
         */

        //approach 2
        if (n < 0)
            x = 1 / x;
        return calculatePower(x, n);
    }

    /**
     * https://www.youtube.com/watch?v=4ivUTBDnki0
     */
    double calculatePower(double number, int power) {
        if (power == 0) {
            return 1;
        }

        double temp = calculatePower(number, power / 2);
        if (power % 2 == 0) {
            //power is even then example - 2^4 = (2^2) * (2^2)
            // here temp is already calculated.
            return temp * temp;
        } else {
            //power is odd, then multiply by number before returning
            //example - 2^3 = 2 * (2^2)
            return number * (temp * temp);
        }
    }

    /*
    Given a non-negative integer c, your task is to decide whether
    there're two integers a and b such that a2 + b2 = c.
    Input: 5 Output: True
    Explanation: 1 * 1 + 2 * 2 = 5

    Input: 3 Output: False
    https://leetcode.com/problems/sum-of-square-numbers/
     */
    static boolean judgeSquareSum(int c) {
        int left = 1;
        int right = (int) Math.sqrt(c);
        int sum = 0;

        while (left <= right) {
            sum = (left * left) + (right * right);
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }

        return false;
    }



    /*
    Given an array with n objects colored red, white or blue, sort them in-place
    so that objects of the same color are adjacent, with the colors in the order red, white and blue.
    Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

    Input: [2,0,2,1,1,0]
    Output: [0,0,1,1,2,2]
    https://leetcode.com/problems/sort-colors/
    https://www.youtube.com/watch?v=uvB-Ns_TVis
     */
    public static void sortColors(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return;
        }

        //here move all the 0 to left and move all 2 to right, then the array will be automatically sorted
        int start_index = 0; //index to store all 0's on left
        int current_index = 0; //moving index
        int end_index = nums.length - 1; //index to store all 2 on right

        while (current_index <= end_index) {
            //check item at current_index in each iteration
            if (nums[current_index] == 0) {
                //swap with the start index
                nums[current_index] = nums[start_index];
                nums[start_index] = 0;
                start_index++;
                current_index++;
            } else if (nums[current_index] == 2) {
                //swap with the end index
                nums[current_index] = nums[end_index];
                nums[end_index] = 2;
                end_index--;

                //don't move current index as the swapped value of
                //current index is yet to be verified in next iteration
            } else {
                current_index++;
            }
        }

        System.out.println("Sorted colors:");
        for (int x : nums) {
            System.out.print(" " + x);
        }
    }

    /**
     * Find the non repeating character in a string.
     * Best approach.
     * <p>
     * Other approach could be having an array of size 26 and keep count of each character
     *
     * @param input
     */
    static void firstNonRepeatingCharacterInString(String input) {
        if (input == null || input.length() == 0) {
            return;
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            //if the last index and first index of current character is not repeating
            //then the current character is the first non repeating character in string
            if (input.indexOf(ch) == input.lastIndexOf(ch)) {
                System.out.println("First non repeating character - " + ch);
                return;
            }
        }

        System.out.println("No non repeating character found in the string.");
    }

    /*
    Find the kth largest element in an unsorted array.
    Note that it is the kth largest element in the sorted order, not the kth distinct element.
    You may assume k is always valid, 1 ≤ k ≤ array's length.

    Input: [3,2,1,5,6,4] and k = 2
    Output: 5

    Input: [3,2,3,1,2,4,5,5,6] and k = 4
    Output: 4

    https://leetcode.com/problems/kth-largest-element-in-an-array/
    https://www.youtube.com/watch?v=FrWq2rznPLQ
     */

    int findKthLargestElement(int[] array, int k) {
        //solution 1
        /*
        Arrays.sort(array);
        int length = array.length;
        return array[length - k];
        */

        //solution 2 - using min heap
        //the min heap will always have minimum item at the root
        //when the elements are added to min heap, they will rearrange in such a way that the minimum is at the root.
        //If the heap size increases the k, then remove root item from the min heap to ensure it has only the k number
        //of items and the root has the minimum.
        //When all the items are added to the heap of size k, then the root will have kth smallest element
        PriorityQueue<Integer> min_queue = new PriorityQueue<>();
        for (int i : array) {
            min_queue.add(i);

            if (min_queue.size() > k) {
                min_queue.remove();
            }
        }

        return min_queue.remove();
    }

    /**
     * Longest palindrome substring using dynamic programming
     * <p>
     * https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
     *
     * @param input
     * @return
     */
    static void longestPalindromeSubstring(String input) {
        if (input == null || input.length() == 0) {
            System.out.println("Invalid input");
            return;
        }

        int str_length = input.length();

        //matrix to hold if a given substring is palindrome or not
        //if substring from index i to j is palindrome, then matrix[i][j] is set to true
        //otherwise matrix[i][j] is set to false
        boolean[][] matrix = new boolean[str_length][str_length];

        int max_length = 0; //max str length of palindrome substring
        int start_index_of_sub_string = 0; //start index of palindrome sub string

        //all substring of str_length 1 is palindrome .. fill the matrix for 1 character substring
        for (int i = 0; i < str_length; i++) {
            matrix[i][i] = true;
            max_length = 1;
            start_index_of_sub_string = i;
        }

        //now check if all the 2 character substrings are palindrome or not
        for (int i = 0; i < str_length - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                matrix[i][i + 1] = true;
                max_length = 2;
                start_index_of_sub_string = i;
            }
        }

        //check for all substring which are of str_length 3 characters or more
        for (int k = 3; k <= str_length; k++) {
            for (int i = 0; i < (str_length - k + 1); i++) {
                int j = i + k - 1;
                //check if the substring from i to j is palindrome or not
                //if char at i and j are same and substring from i+1 to j-1 is palindrome,
                //then the substring from i to j is a palindrome
                if (matrix[i + 1][j - 1] && (input.charAt(i) == input.charAt(j))) {
                    matrix[i][j] = true;

                    if (k > max_length) {
                        max_length = k;
                        start_index_of_sub_string = i;
                    }
                }
            }
        }
        int end_index = start_index_of_sub_string + max_length;
        String output = input.substring(start_index_of_sub_string, end_index);
        System.out.println("Longest substring - " + output);
    }

    /*
        Given two strings s and t, determine if they are isomorphic.
        Two strings are isomorphic if the characters in s can be replaced to get t.

        Input: s = "egg", t = "add"
        Output: true

        Input: s = "foo", t = "bar"
        Output: false

        https://leetcode.com/problems/isomorphic-strings/
        https://www.techiedelight.com/isomorphic-strings/
     */

    static boolean checkIfIsomorphic(String str1, String str2) {
        //check for corner cases
        if (str1 == null && str2 == null) return true;
        if (str1 == null || str2 == null) return false;
        if (str1.length() != str2.length()) return false;

        int length = str1.length();
        Map<Character, Character> map = new HashMap<>(); //map to store the mapping of characters
        Set<Character> set = new HashSet<>(); //to check the occurance of characters

        for (int i = 0; i < length; i++) {
            char x = str1.charAt(i);
            char y = str2.charAt(i);

            //check if x is already appeared in the str1.
            //If yes then check if the y matches value mapped previously from str2
            if (map.containsKey(x)) {
                if (map.get(x) != y) { //don't combine these two if statement
                    System.out.println("Strings are not isomorphic.");
                    return false;
                }
            } else {
                //if character x is appearing for first time .. then y should also appear first time
                //check if y is already found in str2. If yes, then it's not an isomorphic
                if (set.contains(y)) {
                    System.out.println("Strings are not isomorphic.");
                    return false;
                }

                //add to the map and set to keep a tab on occurance of characters
                map.put(x, y);
                set.add(y);
            }
        }

        System.out.println("Strings are isomorphic.");
        return true;
    }

    /**
     * Given an array of integers and an integer k,
     * you need to find the number of unique k-diff pairs in the array.
     * Here a k-diff pair is defined as an integer pair (i, j),
     * where i and j are both numbers in the array and their absolute difference is k.
     *
     * https://leetcode.com/problems/k-diff-pairs-in-an-array/
     * https://www.geeksforgeeks.org/find-a-pair-with-the-given-difference/
     *
     */
    static int findPairs(int[] array, int k){
        int no_of_pairs = 0;
        int i = 0;
        int j = 1;
        int length = array.length;

        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(array);

        while (i < length && j < length){
            int x = array[i];
            int y = array[j];

            if ((i!=j) && Math.abs(x - y) == k){
                List<Integer> list = Arrays.asList(x,y);
                Collections.sort(list);

                set.add(list);
                i++;
                j++;
            } else if ( y - x < k){
                j++;
            } else {
                i++;
            }
        }

        no_of_pairs = set.size();
        System.out.println("Number of pairs - " + no_of_pairs);
        return no_of_pairs;
    }

    /*
    Given an array of n positive integers and a positive integer s,
    find the minimal length of a contiguous subarray of which the sum ≥ s.
    If there isn't one, return 0 instead.

    Example: Input: s = 7, nums = [2,3,1,2,4,3]
            Output: 2
            Explanation: the subarray [4,3] has the minimal length under the problem constraint.

            https://leetcode.com/problems/minimum-size-subarray-sum/
            https://www.youtube.com/watch?v=jKF9AcyBZ6E
     */

    int minimalSumSubarray(int[] array, int sum){
        int left = 0;
        int result = Integer.MAX_VALUE;
        int current_sum = 0;

        for (int i = 0; i < array.length; i++){
            current_sum = current_sum + array[i];

            while(current_sum >= sum){
                result = Math.min(result, (i + 1) - left);
                current_sum = current_sum - array[left];
                left++;
            }
        }

        result = (result != Integer.MAX_VALUE) ? result : 0;

        return result;
    }


    static boolean is_anagram(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        int length = first.length();
        Map<Character, Integer> map = new HashMap<>();

        for (int a = 0; a < length; a++) {
            char x = first.charAt(a);
            if (map.containsKey(x)){
                int count = map.get(x);
                count++;
                map.put(x,count);
            }else{
                map.put(x, 1);
            }
        }

        for (int a = 0; a < length; a++) {
            char x = second.charAt(a);
            if (map.containsKey(x)){
                int count = map.get(x);
                count--;
                map.put(x,count);
            } else {
                return false;
            }
        }

        boolean result = true;
        for (Map.Entry<Character, Integer> entry : map.entrySet()){
            if (entry.getValue() != 0) {
                result = false;
            }
        }

        return result;
    }

    /*
    Convert Sorted Array to Binary Search Tree
    Given an array where elements are sorted in ascending order,
    convert it to a height balanced BST.

    For this problem, a height-balanced binary tree is defined as a binary tree
    in which the depth of the two subtrees of every node never differ by more than 1.

    Given the sorted array: [-10,-3,0,5,9],
    One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

    https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

     */

    Node createBstFromSortedArray() {
        int[] num = {-10, -3, 0, 5, 9};
        Node root = formBSTFromSortedArray(num, 0, num.length - 1);
        return root;
    }

    Node formBSTFromSortedArray(int[] num, int left, int right) {

        //termination condition
        if (left > right) return null;

        //middle number set as root
        int mid = left + (right - left) / 2;

        Node root = new Node(mid);

        //traverse left half of array to form the bst
        root.left = formBSTFromSortedArray(num, left, mid - 1);

        //traverse right half of array to form the bst
        root.right = formBSTFromSortedArray(num, mid + 1, right);

        return root;
    }
}
