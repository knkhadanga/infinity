package main.miscs;

import java.util.*;
import java.util.Stack;

/*
Given a set of time intervals in any order,
merge all overlapping intervals into one and output the result
which should have only mutually exclusive intervals.
Let the intervals be represented as pairs of integers for simplicity.
For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }.
The intervals {1,3} and {2,4} overlap with each other,
so they should be merged and become {1, 4}. Similarly {5, 7} and {6, 8}
should be merged and become {5, 8}

https://www.geeksforgeeks.org/merging-intervals/
 */
public class MergeIntervals {

    /*
    Given a collection of intervals, merge all overlapping intervals.
    Input: [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
    https://leetcode.com/problems/merge-intervals/
     */
    int[][] mergeIntervals(int[][] intervals) {

        if ((intervals == null) || (intervals.length == 0)) {
            System.out.println("Nothing to merge.");
            return intervals;
        }

        //First sort the input array based on the start value of each interval
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);

        Stack<int[]> stack = new Stack<>();

        //push first interval to the stack
        stack.push(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current_interval = intervals[i];
            int[] last_interval = stack.peek();
            //Note - in the array index 0 represents start, and index 1 represents end
            //if the start of current interval is greater than
            //end of last interval, then intervals cannot be merged
            if (current_interval[0] > last_interval[1]) {
                stack.push(current_interval);
                continue;
            }

            //if the start of current interval is less than
            //end of last interval, then intervals can be merged.
            //Hence update the end of last interval to end of current interval (which is on stack)
            if ((current_interval[0] <= last_interval[1]) && (current_interval[1] > last_interval[1])) {
                last_interval[1] = current_interval[1];
            }
        }

        //print the intervals
        stack.stream().forEach(item -> System.out.print("[" + item[0] + "," + item[1] + "] "));
        stack.forEach(item -> System.out.print("[" + item[0] + "," + item[1] + "] "));

        int size = stack.size();
        int[][] output = new int[size][];
        for (int i = 0; i < size; i++) {
            output[i] = stack.pop();
            //System.out.print("[" + output[i][0] + "," + output[i][1] + "] ");
        }

        return output;

    }

    void mergeInterval(Interval[] array) {

        //First sort the input array based on the start value
        Arrays.sort(array, (o1, o2) -> o1.start - o2.start);

        Stack<Interval> stack = new Stack<>();

        //push first item
        stack.push(array[0]);

        for (int i = 1; i < array.length; i++) {

            Interval currentInterval = array[i];
            Interval lastInterval = stack.peek();

            //if the start time of current interval is more than
            //end time of last interval, then push it
            if (currentInterval.start > lastInterval.end) {
                stack.push(currentInterval);
                continue;
            }

            //otherwise .. check for end time of current interval and last interval
            //if current interval end time is > last interval end time, then update the end time
            if (lastInterval.end < currentInterval.end) {
                Interval temp = stack.pop();
                temp.end = currentInterval.end;
                stack.push(temp);
            }
        }

        System.out.println("");
        while (!stack.isEmpty()) {
            Interval x = stack.pop();
            System.out.print("[" + x.start + "," + x.end + "]");
        }
    }

    /*
        Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
        Return the intersection of these two interval lists.

        Also known as merge interval 2

        Input: A = [[0,2],[5,10],[13,23],[24,25]],
               B = [[1,5],[8,12],[15,24],[25,26]]
        Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

        https://leetcode.com/problems/interval-list-intersections/
     */
    public int[][] mergeIntervalII(int[][] A, int[][] B) {

        int i = 0, j = 0;
        List<int[]> output = new ArrayList<>();

        while (i < A.length && j < B.length) {
            int left = Math.max(A[i][0], B[j][0]);
            int right = Math.min(A[i][1], B[j][1]);

            if (left <= right) {
                output.add(new int[]{left, right});
            }

            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;

            }
        }

        System.out.println("Printing merged intervals:");
        output.forEach(entry -> System.out.print("[" + entry[0] + "," + entry[1] + "]"));

        return output.toArray(new int[output.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals test = new MergeIntervals();

        Interval[] array = new Interval[5];
        array[0] = new Interval(1, 3);
        array[1] = new Interval(2, 5);
        array[2] = new Interval(6, 8);
        array[3] = new Interval(9, 13);
        array[4] = new Interval(12, 15);

        //test.mergeInterval(array);

        int[][] intervals = {{1, 3}, {2, 4}, {5, 7}, {6, 8}};
        test.mergeIntervals(intervals);

        int[][] interval1  = {{0,2},{5,10},{13,23},{24,25}};
        int[][] interval2  = {{1,5},{8,12},{15,24},{25,26}};
        //test.mergeIntervalII(interval1, interval2);
    }

}

//data structure to represent interval
class Interval {
    int start;
    int end;

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}