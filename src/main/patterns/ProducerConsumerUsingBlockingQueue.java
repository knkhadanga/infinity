package main.patterns;

import java.util.Random;
import java.util.concurrent.*;

public class ProducerConsumerUsingBlockingQueue {

    int max_size = 5;
    BlockingQueue<Integer> queue;

    ProducerConsumerUsingBlockingQueue(int size) {
        max_size = size;
        queue = new LinkedBlockingDeque<>(max_size);
    }

    void test() throws InterruptedException {
        Runnable p = new Producer(queue);
        Runnable r = new Consumer(queue);

        Thread t1 = new Thread(p, "Producer");
        Thread t2 = new Thread(r, "Consumer");

        t1.start();
        t2.start();

        //t1.join();
        //t2.join();
    }


    public static void main(String[] args) throws InterruptedException {
        new ProducerConsumerUsingBlockingQueue(1).test();
    }

    static class Producer implements Runnable {
        BlockingQueue<Integer> blockingQueue;

        Producer(BlockingQueue<Integer> x) {
            this.blockingQueue = x;
        }

        public void run() {
            Random random = new Random();
            while (true) {
                int x = random.nextInt(5);
                System.out.println("Producer adding - " + x);
                try {
                    blockingQueue.put(x);
                   // Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer implements Runnable {
        BlockingQueue<Integer> blockingQueue;

        Consumer(BlockingQueue<Integer> x) {
            this.blockingQueue = x;
        }

        public void run() {
            while (true) {
                try {
                    System.out.println("Consumer got - " + blockingQueue.take());
                } catch (InterruptedException ie) {

                }
            }
        }
    }
}
