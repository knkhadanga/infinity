package main.miscs;

import java.util.LinkedList;
import java.util.List;

/**
 * Blocking queue implementation.
 *
 * @param <T>
 */
public class BlockingQueue<T> {
    private int maxSize;
    private List<T> list;

    BlockingQueue(int size) {
        this.maxSize = size;
        list = new LinkedList<>();
    }

    public void enqueue(T item) throws InterruptedException {

        synchronized (list) {
            // if the queue already have max allowed number of elements
            // then wait until queue is freed up by dequeue operation.
            while (list.size() == maxSize) {
                System.out.println("Waiting for items to be removed.");
                list.wait();
            }

            /* before adding into the queue, check if the queue is empty.
           If empty, then notify all threads which are waiting for
           dequeue operation.
           */
            if (list.size() == 0) {
                list.notifyAll();
            }

            list.add(item);
        }

    }

    public T dequeue() throws InterruptedException {
        synchronized (list) {
            while (list.size() == 0) {
                list.wait();
            }

        /* if the queue is full then notify all waiting threads (to add)
           that the queue is no longer gonna be available for addition and delete item
           */

            if (list.size() == maxSize) {
                list.notifyAll();
            }
            T item = list.remove(0);
            return item;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> test = new BlockingQueue<>(4);
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);
        test.enqueue(4);
        System.out.println("Adding 5th element");
        test.enqueue(5);
        System.out.println("After adding 5th element");
        test.enqueue(6);
    }
}
