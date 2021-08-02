package main.miscs;

import java.util.LinkedList;
import java.util.Queue;

public class Tries {
    public static void main(String[] args) {
        Tries test = new Tries();
        test.insert("shiva");
        test.insert("shanta");
        test.insert("bramha");
        test.insert("krushna");

        test.search("krushnaa");
    }

    TriesNode<Character> root = null;

    Tries(){
        root = new TriesNode<>();
    }

    void insert (String data){
        if (data == null || data.isEmpty()){
            System.out.println("Invalid input .. returning.");
            return;
        }

        char[] array = data.toLowerCase().toCharArray();
        TriesNode<Character> temp = root;
        for (char c: array){
            temp = insert(c, temp);
        }

        temp.isLastNode = true;
    }

    TriesNode<Character> insert (char input, TriesNode<Character> node){
        int index = input - 'a'; //get the index

        if (node.childrens[index] == null){
            node.childrens[index] = new TriesNode<>();
            node.childrens[index].data = input;
        }

        return node.childrens[index];
    }

    boolean search (String input){
        if (input == null || input.isEmpty()){
            System.out.println("Invalid input .. returning.");
            return false;
        }

        input = input.toLowerCase();
        char[] array = input.toCharArray();

        TriesNode<Character> temp = root;
        for (char c : array){
            temp = search(c, temp);

            if (temp == null){
                System.out.println("\n 1111 - String " + input + " does not exist in tries");
                return false;
            }
        }

        if (!temp.isLastNode){
            System.out.println("\n 2222 - String " + input + " does not exist");
            return false;
        }

        System.out.println("\n 333 - String exist.");
        return  true;
    }

    TriesNode<Character> search (char input, TriesNode<Character> node){
        int index = input - 'a';
        return node.childrens[index];
    }

    void delete(String input){
        if (! search(input)){
            System.out.println("111 - String " + input + " does not exist. Skipping deletion.");
            return;
        }
        char[] array = input.toLowerCase().toCharArray();
        Queue<TriesNode<Character>> queue = new LinkedList<>();

        TriesNode<Character> temp = root;
        for (char c: array){
           temp =  delete(c, temp, queue);

           if (temp == null){ //this should not be case as the string already exist in tries
               System.out.println("222 - Input string " + input + " does not exist in tries. Skipping deletion.");
           }
        }

        
    }

    TriesNode<Character> delete (char c, TriesNode<Character> node, Queue<TriesNode<Character>> queue){
        int index = c - 'a';
        TriesNode<Character> child = node.childrens[index];
        if (child == null){
            return null;
        }

        queue.add(child);
        return child;
    }
}
