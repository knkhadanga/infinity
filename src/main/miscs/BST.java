package main.miscs;

import java.util.*;
import java.util.Queue;
import java.util.Stack;

public class BST {
    public static void main(String[] args) {
        int[] input = {50, 30, 55, 20, 35, 32, 53};
        BST tree = new BST();
        for (int i : input) {
            tree.insert(i);
        }


       // System.out.println("max = " + tree.findMax());
       // System.out.println("min = " + tree.findMin());

        //System.out.print("\nInorder traversal   - ");
        //tree.inOrderTraversal();

        //System.out.print("\nPreorder traversal  - ");
        //tree.preOrderTraversal();
        //tree.preOrderTraversalNonRecursive();

        //System.out.print("\nPostorder traversal - ");
        //tree.postOrderTraversal();
        //System.out.println("");
        //tree.postOrderTraversalNonRecursive();

        //System.out.print("\n Levelorder traversal - ");
        //tree.levelOrderTraversal();
        //System.out.println("");
        tree.reverseLevelOrderTraversal();

        //System.out.print("\nzig zag traversal - ");
        //tree.zigZagTraversal();

        //System.out.println("\n Is BST - " + tree.isBST());

        //tree.printAllPath();
        //System.out.println("-----------");

        // tree.minimumDepthOfBinaryTree();


        //tree.isBalanced()

        // tree.isMinHeap();
        //tree.sizeOfTree();
        //tree.sizeOfTreeNonRecursive();

        //System.out.println("\n Is BST - " + tree.isBST());

        //tree.leftViewOfTree();
    }

    Node root;

    void insert(int input) {
        if (root == null) {
            root = new Node();
            root.data = input;
            return;
        }

        insert(root, input);
    }

    void insert(Node node, int input) {
        if (node.data > input) { //insert on left subtree
            if (node.left == null) {
                node.left = new Node();
                node.left.data = input;
                return;
            }

            insert(node.left, input);
        } else if (input > node.data) { //insert on right subtree
            if (node.right == null) {
                node.right = new Node();
                node.right.data = input;
                return;
            }

            insert(node.right, input);
        } else {
            System.out.println("Duplicate Input..");
            return;
        }
    }

    int findMin() {
        Node temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }

        return temp.data;
    }

    int findMin(Node root) {
        Node temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }

        return temp.data;
    }

    int findMax() {
        Node temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }

        return temp.data;
    }

    void preOrderTraversal() {
        preOrderTraversal(root);
    }

    void inOrderTraversal() {
        inOrderTraversal(root);
    }

    void postOrderTraversal() {
        postOrderTraversal(root);
    }

    void preOrderTraversal(Node node) { //root - left - right
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");

        preOrderTraversal(node.left);

        preOrderTraversal(node.right);
    }

    void preOrderTraversalNonRecursive() { //root -> left -> right
        if (root == null) {
            System.out.println("No data to print in the tree");
            return;
        }

        //create a stack
        Stack<Node> stack = new Stack<>();

        //add the root node
        stack.add(root);

        while (!stack.isEmpty()) {
            //get the node on the top
            Node temp = stack.pop();

            //print node data
            System.out.print(temp.data + " ");

            //add right and left node if they are empty
            //(so when popped it should be left to right order)
            if (temp.right != null) stack.add(temp.right);

            if (temp.left != null) stack.add(temp.left);
        }
    }


    void inOrderTraversal(Node node) { //left - root - right
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);

        System.out.print(node.data + " ");

        inOrderTraversal(node.right);
    }

    void inOrderTraversalNonRecursive() { //left - root - right
        if (root == null) {
            System.out.println("Tree has nothing to print.");
            return;
        }

        Node currentNode = root;
        Stack<Node> stack = new Stack<>();

        //run until the current node is not null or stack is not empty
        while (currentNode != null || !stack.isEmpty()) {
            //push until the current left is null
            while (currentNode != null) {
                stack.add(currentNode);
                currentNode = currentNode.left;
            }

            //if current node is null, then pop the latest item from stack
            Node temp = stack.pop();
            System.out.print(temp.data + " ");

            //traverse the right subtree from current the node
            currentNode = temp.right;
        }
    }


    void postOrderTraversal(Node node) { //left - right - root
        if (node == null) {
            return;
        }

        postOrderTraversal(node.left);

        postOrderTraversal(node.right);

        System.out.print(node.data + " ");
    }

    /*
    https://www.youtube.com/watch?v=qT65HltK2uE
     */
    void postOrderTraversalNonRecursive() {
        if (root == null) {
            System.out.println("Tree has nothing to print.");
            return;
        }

        Node currentNode = root;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(currentNode);
        while (!stack1.isEmpty()) {
            Node temp = stack1.pop();

            stack2.push(temp);
            if (temp.left != null) stack1.push(temp.left);
            if (temp.right != null) stack1.push(temp.right);
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }


    /**
     * Level order traversal of the tree
     */
    void levelOrderTraversal() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.peek();
            System.out.print(temp.data + " ");
            queue.remove();

            if (temp.left != null) {
                queue.add(temp.left);
            }

            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }

    /**
     * Travelling the tree in reverse level order traversal
     * i.e.: From bottom to top (direction does not change .. it is from left to right)
     * but from bottom to up.
     * https://www.youtube.com/watch?v=ZcbxJ2HO2EQ&t=3s
     */
    void reverseLevelOrderTraversal() {

        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();

        //add root to the queue
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.remove();

            //add the node to the stack
            stack.add(temp);

            //add the child of temp from right to left in the queue
            if (temp.right != null) queue.add(temp.right);
            if (temp.left != null) queue.add(temp.left);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().data + " ");
        }
        System.out.println("");
    }

    void addToStack(Stack<Node> stack, Node node) {
        if (node != null) {
            stack.add(node);
        }
    }

    void height() {
        int x = height(root);
        System.out.println("\n Height - " + x);
    }

    int height(Node node) {
        if (node == null) {
            return 0;
        }

        int leftSubtreeHeight = height(node.left);
        int rightSubtreeHeight = height(node.right);

        int maxValue = Math.max(leftSubtreeHeight, rightSubtreeHeight);
        return (1 + maxValue);
    }

    void heightNonRecursive() {
        int height = heightNonRecursive(root);
        System.out.println("Height non recursive - " + height);
    }

    private int heightNonRecursive(Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();

        int height = 0;

        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Node temp = queue.poll();
                if (temp.left != null) {
                    ((LinkedList<Node>) queue).add(temp.left);
                }

                if (temp.right != null) {
                    ((LinkedList<Node>) queue).add(temp.right);
                }
            }

            height++;
        }

        return height;
    }

    void delete(int data) {
        delete(root, data);
    }

    void delete(Node root, int data) {
        if (root == null) {
            return;
        }

        Node parent = null;
        Node current = root;

        //find the node having the data and it's parent
        while (current != null && current.data != data) {
            parent = current;

            if (current.data > data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        //if dota not found, then return
        if (current == null) {
            System.out.println("Data not found in tree. Returning.");
            return;
        }

        //if the node have no child
        if (current.left == null && current.right == null) {

            if (current == root) {
                root = null;
                return;
            }

            if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.left == null || current.right == null) { //if node has one child

            //find the only child of the node to be deleted
            Node child = (current.left != null) ? current.left : current.right;
            if (current == root) {
                root = child;
                return;
            }

            //reset the child reference
            if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }

            current = null;
        } else { //the node has two child
            //find the value to be replaced with
            int dataToBeReplaced = findMin(current.right);

            //delete the node containing the dataToBeReplaced
            delete(root, dataToBeReplaced);

            //replace the current node data
            current.data = dataToBeReplaced;
        }

    }

    void zigZagTraversal() {
        if (root == null) {
            System.out.println("root node is empty.. cannot traverse.");
            return;
        }

        //create two stacks .. one to hold current level node,
        // and the other to hold next level nodes
        Stack<Node> current_level = new Stack<>();
        Stack<Node> next_level = new Stack<>();
        boolean traverse_left_to_right = true;

        current_level.add(root);

        while (!current_level.isEmpty()) {
            while (!current_level.isEmpty()) {
                Node temp = current_level.pop();
                System.out.print(temp.data + " ");
                if (traverse_left_to_right) {
                    addNode(next_level, temp.left);
                    addNode(next_level, temp.right);
                } else {
                    addNode(next_level, temp.right);
                    addNode(next_level, temp.left);
                }
            }

            //change the direction
            traverse_left_to_right = !traverse_left_to_right;

            //swap the stack reference
            Stack<Node> temp = current_level;
            current_level = next_level;
            next_level = temp;
        }
    }

    void addNode(Stack<Node> stack, Node node) {
        if (node != null) {
            stack.push(node);
        }
    }


    void createMirror() {
        createMirror(root);
    }

    /**
     * Method to convert a given tree to it's Mirror
     *
     * @param currentNode
     * @return
     */
    Node createMirror(Node currentNode) {
        if (currentNode == null) return currentNode;

        //create mirror recursively for left subtree
        Node leftNode = createMirror(currentNode.left);
        //create mirror recursively for right subtree
        Node rightNode = createMirror(currentNode.right);

        //swap the left with right in the current node
        currentNode.left = rightNode;
        currentNode.right = leftNode;

        //return the value
        return currentNode;
    }

    /**
     * Method to check if two trees are mirror image of each other
     *
     * @param a Root node of first tree
     * @param b Root bode of 2nd treea
     * @return
     */
    boolean isMirror(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }

        //if one of the node is null
        if (a == null || b == null) {
            return false;
        }

        if ((a.data == b.data) &&
                isMirror(a.left, b.right) &&
                isMirror(a.right, b.left)) {
            return true;
        }

        return false;
    }

    List<String> printAllPath() {
        List<String> allPaths = new ArrayList<>();

        printAllPath(root, "", allPaths);
        for (String path : allPaths) {
            System.out.println(path);
        }

        return allPaths;
    }

    void printAllPath(Node node, String path, List<String> allPaths) {

        //append the current node information
        path += node.data;

        //check if the node is a leaf node and add current path to
        //the list if it is a leaf node
        if (node.left == null && node.right == null) {
            allPaths.add(path);
            return;
        }

        if (node.left != null) {
            printAllPath(node.left, path + "->", allPaths);
        }

        if (node.right != null) {
            printAllPath(node.right, path + "->", allPaths);
        }

    }
    

    /**
     * Method to check if the given binary tree is a BST
     * https://www.youtube.com/watch?v=2HqICp1nk6s&list=PLiKk3Hq32QPKLUYMeLsEIP556baLXOHpv&index=35&t=0s
     *
     * @return
     */
    boolean isBST() {
        //root node can have maximum value Integer.Max and minimum value of Integer.Min
        //return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return isBinarySearchTree(root, null, null);
    }

    boolean isBST(Node currentNode, int minValue, int maxValue) {
        if (currentNode == null) return true;

        //the currentNode data should always be in between max and min value.
        //the left node data can have max value less than current node
        //and right node data should have minimum value greater than current node data
        if (currentNode.data >= minValue
                && currentNode.data < maxValue
                && isBST(currentNode.left, minValue, currentNode.data)
                && isBST(currentNode.right, currentNode.data, maxValue)
        ) {
            return true;
        }

        return false;
    }

    boolean isBinarySearchTree(Node node, Integer minValue, Integer maxValue) {
        if (node == null) return true;

        int current_node_data = node.data;

        //if minimum value is not null and current node data is less than equal minimum, then it's not a bst
        if (minValue != null && current_node_data <= minValue) return false;

        //if max value is not null and current node data is less than equal to maximum, then it's not a bst
        if (maxValue != null && current_node_data >= maxValue) return false;

        //the left tree should have max value less than current node data
        if (!isBinarySearchTree(node.left, minValue, current_node_data)) return false;

        //the right tree should have min value greater than current node data
        if (!isBinarySearchTree(node.right, current_node_data, maxValue)) return false;

        return true;
    }


    /*
    Given a binary tree, find its minimum depth.The minimum depth is the number of nodes
    along the shortest path from the root node down to the nearest leaf node.
    Note: A leaf is a node with no children.
    LeetCode Ref: https://leetcode.com/problems/minimum-depth-of-binary-tree/
    https://www.youtube.com/watch?v=TdkUawVt1So
     */
    void minimumDepthOfBinaryTree() {
        if (root == null) {
            System.out.println("Minimum depth is = 0");
            return;
        }

        //create a wrapper class to store the node and it's current depth
        //and perform level order traversal
        Queue<NodeWrapper> queue = new LinkedList<>();

        //add root node
        NodeWrapper wrapRoot = new NodeWrapper(root, 1);//depth of root is 1
        queue.add(wrapRoot);

        while (!queue.isEmpty()) {
            NodeWrapper temp = queue.remove();
            //check if it is a leaf node, if yes, then depth of
            //the node is the minimum depth
            if (temp.node.left == null && temp.node.right == null) {
                System.out.println("Minimum depth is = " + temp.depth);
                return;
            }

            //otherwise add the child nodes of current level and increment the depth
            if (temp.node.left != null) {
                queue.add(new NodeWrapper(temp.node.left, temp.depth + 1));
            }

            if (temp.node.right != null) {
                queue.add(new NodeWrapper(temp.node.right, temp.depth + 1));
            }
        }

        System.out.println("Minimum depth is = 0");
    }

    static class NodeWrapper {
        Node node;
        int depth;

        NodeWrapper(Node n, int d) {
            node = n;
            depth = d;
        }
    }


    /*
    https://www.geeksforgeeks.org/diameter-of-a-binary-tree-in-on-a-new-method/
     */
    void diameterOfBinaryTree() {
        Diameter output = new Diameter();
        getDiameterUsingHeight(root, output);
        System.out.println("Diameter of binary tree - " + output.diameter);
    }

    int getDiameterUsingHeight(Node node, Diameter obj) {
        if (node == null) {
            return 0;
        }

        //get the height of left subtree at a given node
        int left_height = getDiameterUsingHeight(node.left, obj);
        //get the height of right subtree at a given node
        int right_height = getDiameterUsingHeight(node.right, obj);

        //calculate diameter of tree at every node using height and
        //store the diameter in the output
        int current_diameter = 1 + left_height + right_height;

        //update the diameter object if the current diameter is
        //maximum than diameters calculated so far
        obj.diameter = Math.max(obj.diameter, current_diameter);

        //get the height of tree at every level and return
        int height = 1 + Math.max(left_height, right_height);

        return height;
    }

    static class Diameter {
        //initialize with minimum value
        int diameter = Integer.MIN_VALUE;
    }

    /**
     * Method to check if a given tree height is balanced or not.
     * Balanced height means, the max height difference between left
     * and right subtree should not be more than 1. This is applicable
     * for all the sub trees in binary tree.
     *
     * @return
     */
    void isBalanced() {
        boolean isBalanced = isBalanced(root);
        System.out.println("\nIs tree balanced - " + isBalanced);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        //get height of both left and right sub tree and check the difference
        int left_tree_height = height(node.left);
        int right_tree_height = height(node.right);

        //recursively check for all subtree
        if ((Math.abs(left_tree_height - right_tree_height) <= 1) // height difference should not be more than 1
                && isBalanced(node.left)
                && isBalanced(node.right)) {
            return true;
        }

        return false;
    }

    /**
     * Check if the given binary tree is minimum heap.
     * https://www.techiedelight.com/check-binary-tree-is-min-heap/
     *
     * @return
     */
    boolean isMinHeap() {
        //boolean to check if the heap properties is violated or not
        //where right child should not exist if there is no left child of a given node
        //and if there is no right child exist, then there should not be any sub sequent left child as well
        boolean null_seen = false;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            //check for left child node
            if (currentNode.left != null) {
                if (null_seen || currentNode.data <= currentNode.left.data) {
                    System.out.println("It's not a min heap");
                    return false;
                }

                queue.add(currentNode.left);
            } else {
                //if there is no left child of current node, then there should not exist any right child
                null_seen = true;
            }

            if (currentNode.right != null) {
                //
                if (null_seen || currentNode.data <= currentNode.right.data) {
                    System.out.println("It's not a min heap");
                    return false;
                }

                queue.add(currentNode.right);
            } else {
                //no right child exist
                //once the right child is null, then there should not exist any left child
                null_seen = true;
            }
        }

        System.out.println("It is a minimum heap.");
        return true;
    }

    /*
        Size is the number of nodes that exist in the binary search tree.
     */
    void sizeOfTree() {
        int size = size(root);
        System.out.println("(Recursive) Size of the tree is = " + size);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return (1 + size(node.left) + size(node.right));
        }
    }

    void sizeOfTreeNonRecursive (){
        Queue<Node> queue = new LinkedList<>();
        queue.add (root);
        int size = 0;

        while (!queue.isEmpty()){
            int x = queue.size();
            size = size + x;

            while (x > 0){
                Node t = queue.poll();
                if (t.left != null) queue.add (t.left);
                if (t.right != null) queue.add (t.right);
                x--;
            }
        }

        System.out.println("(Non recursive) Size of tree is = " + size);
    }

    /*
    Print left view of the tree.  (i.e.: First item from left in every level)
                1
               /\
 --->        2    3
 --->       /\   /\
           4  5 6  7

  Left view is view for above tree is 1, 2, 4 (i.e. First element from left in every level)
  https://www.techiedelight.com/print-left-view-of-binary-tree/
     */
    void leftViewOfTree() {
        //do a lever order traversal and only print the first item from left
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); //number of node in each level
            int count = 0; //node number which is currently getting processed

            while (count < size) {
                //remove the item from queue
                Node currentNode = queue.poll();

                //increment the count to identify which node is being processed
                count++;

                if (count == 1) {
                    //print it .. this is the first item from left in every level
                    System.out.println(currentNode.data + " ");
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }
    }

    boolean is_min_heap(){
        boolean is_null_seen = false;
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()){
            Node x = queue.poll();

            if (x.left != null){
                if (is_null_seen || x.data >= x.left.data){
                    return false;
                }

                queue.add(x.left);
            } else {
                is_null_seen = true;
            }

            if (x.right != null){
                if (is_null_seen || x.data >= x.right.data){
                    return false;
                }
                queue.add(x.right);
            } else{
                is_null_seen = true;
            }
        }

        return true;

    }

    /*
    https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
    https://www.youtube.com/watch?v=vssbwPkarPQ
    Given the root of a binary tree, flatten the tree into a "linked list":
    * The "linked list" should use the same TreeNode class where the right child pointer
      points to the next node in the list and the left child pointer is always null.

    * The "linked list" should be in the same order as a pre-order traversal of the binary tree.

            1
           / \
         2     5    ----> 1-2-3-4-5-6
        / \     \
       3  4      6

     */
    void flatten_binary_tree(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current_node = stack.pop();
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

    /**
     * https://leetcode.com/problems/serialize-and-deserialize-bst/
     * https://www.youtube.com/watch?v=jFL-5h7ETPE
     */
    final String no_node = "N";
    final String delimeter = ",";

    Node serialize_deserialize_bst(Node root){
        String tree = serialize_binary_tree(root);
        return deserialize_binary_tree(tree);
    }

    String serialize_binary_tree(Node root) {
        if (root == null) return null;
        StringBuilder stb = new StringBuilder();
        serialize_preorder(root, stb);
        return stb.toString();
    }

    void serialize_preorder(Node root, StringBuilder stb) {
        if (root == null) {
            stb.append(no_node).append(delimeter);
        } else {
            stb.append(root.data).append(delimeter);
            serialize_preorder(root.left, stb);
            serialize_preorder(root.right, stb);
        }
    }

    Node deserialize_binary_tree(String input) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(input.split(delimeter)));
        return deserialize_helper(queue);
    }

    Node deserialize_helper(Queue<String> queue) {
        String data = queue.remove();
        if (data == no_node) {
            return null;
        } else {
            Node root = new Node();
            root.data = Integer.parseInt(data);
            root.left = deserialize_helper(queue);
            root.right = deserialize_helper(queue);
            return root;
        }
    }

}
