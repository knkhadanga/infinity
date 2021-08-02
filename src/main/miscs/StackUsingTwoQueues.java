package main.miscs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement stack using two different queues.
 * Approach 1 - Insertion is slow and removal is fast
 * Approach 1 - Insertion is fast and removal is slow
 */
public class StackUsingTwoQueues<T> {

    public static void main(String[] args) {
        StackUsingTwoQueues<Integer> test = new StackUsingTwoQueues<>();
        for (int i = 0; i < 5; i++) {
            System.out.println("Pushing - " + i);
            test.pushX(i);
        }

        int x = 0;
        while (x++ < 5) {
            System.out.print(test.popX() + " ");
        }

    }


    Queue<T> queue1 = new LinkedList<>();
    Queue<T> queue2 = new LinkedList<>();

    /**
     * In this approach the insertion is slow.
     *
     * @param data
     */
    void push(T data) {
        //add the data to queue2
        queue2.add(data);

        //remove all the data from queue1 and add it to queue2
        while (!queue1.isEmpty()) {
            queue2.add(queue1.poll());
        }

        //swap q1 to q2
        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        //System.out.println("\n"+queue1.size() + " " + queue2.size());
    }

    /**
     * In this approach the pop is faster
     *
     * @return
     */
    T pop() {
        //always remove from queue1
        return queue1.poll();
    }


    /**
     * In this approach the insertion is fast.
     *
     * @param data
     */
    void pushX(T data) {
        //add the data to queue1
        queue1.add(data);
    }

    /**
     * In this approach the pop is slower
     *
     * @return
     */
    T popX() {
        if (queue1.size() == 0) {
            return null;
        }

        //remove items from queue1 until there is only one item left
        //in the queue and insert it to queue2
        while (queue1.size() != 1) {
            queue2.add(queue1.remove());
        }

        //get the last item (this has to be popped)
        T item = queue1.remove();

        //swap queue1 and queue2
        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return item;
    }
}
