package main.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class FindAvgInLevel {

    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    /*
    https://leetcode.com/problems/average-of-levels-in-binary-tree/

    Given the root of a binary tree, return the average value of the nodes
    on each level in the form of an array.
    Answers within 10-5 of the actual answer will be accepted.
                     3
                  /    \
                 9      20
                       /  \
                      15   7

    Input: root = [3,9,20,null,15,7]
    Output: [3.00000,14.50000,11.00000]
    Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
    Hence return [3, 14.5, 11].
     */
    List<Double> find_average_in_level(TreeNode root){
        List<Double> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        int size = 0;
        double sum = 0;
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if (temp != null) {
                sum = sum + temp.val;
                size ++;

                if (temp.left != null) {
                    queue.add(temp.left);
                }

                if (temp.right != null) {
                    queue.add(temp.right);
                }
            } else if (!queue.isEmpty()){
                sum = sum / size;
                result.add(sum);
                sum = 0;
                size = 0;
                queue.add(null);
            }
        }

        if (size > 0) {
            sum = sum / size;
            result.add(sum);
        }

        return result;

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}