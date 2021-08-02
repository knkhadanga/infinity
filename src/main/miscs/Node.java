package main.miscs;

/**
 * Generic node can be used while forming lists and trees
 */
public class Node {

    //no arg constructor
    public Node () {

    }

    //constructor with one arg
    public  Node (int data) {
        this.data = data;
    }

    public int data;
    public char charData;
    public String strData;

    //to be used in linked list
    public Node previous;
    public Node next;

    //to be used in tree
    public Node left;
    public Node right;

}
