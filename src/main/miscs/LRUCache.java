package main.miscs;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<T> {
    NodeG<T> head;
    NodeG<T> lastNode;
    Map<T, NodeG<T>> map;

    int cacheSize;

    LRUCache(int size){
        this.cacheSize = size;
        map = new HashMap<>();
    }

    T getData (T key){
        NodeG<T> temp;
        if (map.containsKey(key)){
            temp = map.get(key);
        }else{
            //get it from disk
            temp = getDataFromDisk(key);
        }

        refreshCache(key, temp);
        return temp.data;
    }

    void refreshCache(T key, NodeG<T> inputNode){
        if (map.containsKey(key)){
            NodeG<T> temp = map.get(key);
            if (temp == head){
                //already on the front, no ops
                return;
            }

            //bring it to the front
            NodeG<T> before = temp.previous;
            NodeG<T> after = temp.next;

            before.next = after;
            if (after!= null){
                after.previous = before;
            }else {
                //reset last node pointer
                lastNode = before;
            }

            temp.next = head;
            temp.previous = null;
            head.previous = temp;
            head = temp;
            return;
         }


        if (head == null){
            head = inputNode;
            lastNode = inputNode;
            map.put(key, head);
            return;
        }

        if (map.size() == cacheSize){
            //delete last node
            System.out.println("removing last node " + lastNode.data);
            NodeG<T> beforeLast = lastNode.previous;
            T lastNodeKey = lastNode.key;
            lastNode.previous = null;
            lastNode = beforeLast;
            beforeLast.next = null;
            map.remove(lastNodeKey);
        }

        inputNode.next = head;
        head.previous = inputNode;
        head = inputNode;
        map.put(key, inputNode);
    }

    void print () {
        NodeG<T> temp = head;
        while (temp != null) {
            System.out.print(temp.key + "->");
            temp = temp.next;
        }
        System.out.print("NULL");
        System.out.println("\n Last node = "+lastNode.data);
    }

    /**
     * Just a place holder to return some dummy data
     * @param key
     * @return
     */
    NodeG<T> getDataFromDisk(T key){
        NodeG<T> temp = new NodeG<>();
        temp.data = key;
        temp.key = key;
        return temp;
    }

    public static void main(String[] args) {
        LRUCache<Integer> test = new LRUCache<>(4);
        for (int x = 0; x < 6; x++){
            test.getData(x);
        }

        test.print();
        test.getData(2);
        test.print();
        test.getData(4);
        test.print();
        test.getData(3);
        test.print();
        test.getData(6);
        test.print();
        test.getData(6);
        test.print();
        test.getData(8);
        test.print();
    }
}
