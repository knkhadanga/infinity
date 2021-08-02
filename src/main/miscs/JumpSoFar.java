package main.miscs;

/*
Given an array of non-negative integers, you are initially positioned at the
first index of the array. Each element in the array represents your maximum
jump length at that position. Determine if you are able to reach the last index.

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.

https://leetcode.com/problems/jump-game/

 */
public class JumpSoFar {

    boolean jumpSoFar (int[] array){
        if (array == null || array.length == 0){
            return false;
        }

        int length = array.length;
        int jumped_so_far = 0;

        for (int current_index = 0; current_index < length; current_index++){
            //if maxJumpSoFar already reached till last index
            //or maxJumpSoFar is less than the current index
            //then break
            if ((jumped_so_far >= length - 1) || (jumped_so_far < current_index)){
                break;
            }

            //max steps can be jumped from current index
            jumped_so_far = Math.max(jumped_so_far, (array[current_index] + current_index));
        }

        if (jumped_so_far >= length - 1){
            return  true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        JumpSoFar test = new JumpSoFar();
        int[] array1 = {2,3,1,1,4};
        int[] array2 = {3,2,1,0,4};
        //System.out.println("Array1 = " + test.jumpSoFar(array1));
        System.out.println("Array2 = " + test.jumpSoFar(array2));
    }

}
