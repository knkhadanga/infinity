package main.trees;

import java.util.Stack;

public class FlattenBinaryTree {
    /*
    https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
    https://www.youtube.com/watch?v=vssbwPkarPQ

    Given the root of a binary tree, flatten the tree into a "linked list":

     * The "linked list" should use the same TreeNode class where the right child pointer
       points to the next node in the list and the left child pointer is always null.
     * The "linked list" should be in the same order as a pre-order traversal of the binary tree.

            1
           / \
          2   3  -->  1 -> 2 -> 3 -> 4 -> 5
             / \
            4   5
    */

    void flatten_tree(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current_node = stack.pop();
            if (current_node.right != null) {
                stack.push(current_node.right);
            }

            if (current_node.left != null) {
                stack.push(current_node.left);
            }

            if (!stack.isEmpty()) {
                current_node.right = stack.peek();
            }

            current_node.left = null;
        }
    }

}
