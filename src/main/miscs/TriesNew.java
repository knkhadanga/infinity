package main.miscs;

import java.util.HashMap;
import java.util.Map;

public class TriesNew {
    TriesNode root;

    TriesNew (){
        root= new TriesNode();
    }

    class TriesNode {
        Map<Character, TriesNode> map = new HashMap<>();
        boolean isLast;
        char data;
    }

    void insert(String input){
        if (input == null || input.isEmpty()){
            System.out.println("Invalid input");
            return;
        }

        input = input.toLowerCase();
        char[] array = input.toCharArray();
        TriesNode currentNode = root;

        for (char ch: array){
            if (!currentNode.map.containsKey(ch)){
                TriesNode temp = new TriesNode();
                temp.data = ch;
                currentNode.map.put(ch, temp);
                currentNode = temp;
            }else {
                currentNode = currentNode.map.get(ch);
            }
        }

        currentNode.isLast = true;
    }

    boolean search (String input){
        if (input == null || input.isEmpty()){
            System.out.println("Invalid input");
            return false;
        }

        input = input.toLowerCase();
        char array[] = input.toCharArray();

        TriesNode current = root;

        for (char ch: array){
            if (!current.map.containsKey(ch)){
                System.out.println("Does not have the word");
                return false;
            }

            current = current.map.get(ch);
        }

        if (current.isLast){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        TriesNew test = new TriesNew();
        test.insert("abc");
        test.insert("abcd");
        test.insert("ab");
        test.insert("abcde");

        System.out.println("Has input abcde  - " + test.search("abde"));
    }
}
