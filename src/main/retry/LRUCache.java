package main.retry;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<T> {

    int cacheSize;
    Map<T, Node<T>> map;
    Node<T> head;
    Node<T> last;

    LRUCache(int size) {
        this.cacheSize = size;
        map = new HashMap<>();
    }

    T getFromCache(T key) {

        if (map.containsKey(key)) {
            Node<T> temp = map.get(key);

            //if it is head, then no need to reset order in the LRU Cache
            if (temp == head) {
                return temp.value;
            }

            //bring the item to front
            bringToTheFront(temp);

            return temp.value;
        }

        //if data does not exist in cache, get it from disk
        Node<T> temp = getDataFromDisk(key);
        if (head == null) {
            head = temp;
            last = temp;
            map.put(temp.key, temp);
            return temp.value;
        }

        //bring the item to front
        addToCache(temp);

        //and return value
        return temp.value;
    }

    private void bringToTheFront(Node<T> currentNode) {

        Node<T> previous = currentNode.before;
        Node<T> after = currentNode.next;

        if (currentNode == last) {
            last = previous;
        }

        previous.next = after;
        if (after != null) {
            after.before = previous;
        }

        currentNode.before = null;
        currentNode.next = head;
        head = currentNode;
    }

    private void addToCache(Node<T> node) {
        node.next = head;
        head.before = node;
        head = node;

        map.put(node.key, node);

        //check if the cache size has exceeded
        if (map.size() > cacheSize) {
            Node temp = last.before;
            T key = last.key;

            last.before = null;
            temp.next = null;
            last = temp;
            map.remove(key);
        }
    }

    private Node<T> getDataFromDisk(T key) {
        Node<T> temp = new Node<>();
        temp.value = key;
        temp.key = key;

        return temp;
    }

    void display() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    class Node<T> {
        T key;
        T value;

        Node<T> next;
        Node<T> before;
    }

    public static void main(String[] args) {
        LRUCache<Integer> test = new LRUCache<>(4);
        test.getFromCache(1);
        test.display();
        test.getFromCache(2);
        test.display();
        test.getFromCache(3);
        test.display();
        test.getFromCache(4);
        test.display();
        test.getFromCache(5);
        test.display();
        test.getFromCache(3);

        test.display();
        test.getFromCache(6);
        test.display();
        test.getFromCache(7);
        test.display();


    }
}
