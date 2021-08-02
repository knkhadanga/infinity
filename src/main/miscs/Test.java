package main.miscs;

import java.util.*;

public class Test {

    // Recursive function to print all possible subarrays for given array
    static void printSubArrays(int[] arr, int start, int end) {
        List<Integer> list = new ArrayList<>();

        // Stop if we have reached the end of the array
        if (end == arr.length)
            return;

            // Increment the end point and start from 0
        else if (start > end)
            printSubArrays(arr, 0, end + 1);

            // Print the subarray and increment the starting point
        else {
            //  System.out.println("Start - " + start + " End " + end);
            //System.out.println("");
            System.out.print("[");
            for (int i = start; i < end; i++) {
                System.out.print(arr[i] + ", ");
            }
            System.out.print(arr[end] + "]");
            //System.out.println("");
            printSubArrays(arr, start + 1, end);
        }

        return;
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

    static void printSubArray(int[] array, int startIndex, int endIndex){
        //if start index reached end of the list, then return
        if (startIndex == array.length){
            return;
        } else if (startIndex > endIndex) {
            //or start index crossed the end index, then increment end index and reset start index to 0
            //for a new sub array to be printed
            printSubArray(array, 0, endIndex+1);
        } else {
            //print the sub array
            System.out.print("[");
            for (int i = startIndex; i < endIndex; i++){
                System.out.print(array[i]+", ");
            }
            System.out.print(array[endIndex] + "]");

            //increment the start index to print another sub array
            printSubArray(array, startIndex+1, endIndex);
        }
    }

    static void printSubArray(){
        int[] array = {1,2,3};
        printSubArray(array, 0, 0);
    }



    static void test (String input){
        int length = input.length();
       // input = input.toLowerCase();
        int[] array = new int[26];
        int sum = 0;
        for (int i = 0; i < length; i++){
            char ch = input.charAt(i);
            if (Character.isDigit(ch)){
                sum = sum + (ch - '0');
                continue;
            }

            array[ch -'A']++;
        }


        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < 26; i++){
            int count = array[i];
           // System.out.println("I " + i + " Count " + count);
            while (count > 0){
                char x = (char) (i + 'A');
                stb.append(x);
                count--;
            }
        }

        String output = stb.toString().toUpperCase();
        output = output + sum;

        System.out.println("Output - " + output);
    }

    public static void main(String args[]) {
        int[] arr = {1, 2, 3};
        // printSubArrays(arr, 0, 0);
        // System.out.println();
        // printSubArray();
        //System.out.println("");
        //printAllSubsets(arr);
        String x = "ABC123DEF";
        test(x);

        int i = 0;
        int size = 3;
        while (i++ < size){
            System.out.println("I " + i);
        }
    }
}

