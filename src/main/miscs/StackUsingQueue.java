package main.miscs;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue<T> {

    Queue<T> queue1 = new LinkedList<>();
    Queue<T> queue2 = new LinkedList<>();

    void push(T item) {
        queue2.add(item);

        while (!queue1.isEmpty()) {
            queue2.add(queue1.remove());
        }

        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    T pop() {
        return queue1.poll();
    }

    void pushNew(T item) {
        queue1.add(item);
    }

    T popNew() {
        if (queue1.size() == 0) {
            return null;
        }

        while (queue1.size() != 1) {
            queue2.add(queue1.remove());
        }

        T item = queue1.remove();

        Queue temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return item;
    }

    public static void main(String[] args) {
        StackUsingQueue<String> stack = new StackUsingQueue<>();
        stack.pushNew("hello");
        stack.pushNew("world");
        stack.pushNew("test");

        System.out.println(stack.popNew() + " " + stack.popNew() + " " + stack.popNew());
    }
}
