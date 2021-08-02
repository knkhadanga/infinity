package main.miscs;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer {
    int MAX_SIZE = 10;
    List<Integer> resource = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer test = new ProducerConsumer();
        test.runProducerConsumer();
    }

    public void runProducerConsumer() throws InterruptedException {
        Runnable r1 = new Producer(resource);
        Runnable r2 = new Consumer(resource);

        Thread t1 = new Thread(r1, "Producer");
        Thread t2 = new Thread(r2, "Consumer");
        Thread t3 = new Thread(r2, "Consumer1");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }

    class Producer implements Runnable {
        List<Integer> resource;
        int number = 10;

        Producer(List<Integer> list) {
            resource = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (resource) {
                    while (resource.size() == MAX_SIZE) {
                        try {
                            resource.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    int x = (number + 1) % 10;
                    number = number + 1;
                    System.out.println("producer adding - " + x);
                    resource.add(x);

                    resource.notifyAll();
                    /*
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                     */
                }
            }
        }

    }

    class Consumer implements Runnable {
        List<Integer> resource;

        Consumer(List<Integer> list) {
            resource = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (resource) {
                    while (resource.size() == 0) {
                        try {
                            resource.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    int x = resource.remove(0);
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " extracted = " + x);

                    resource.notifyAll();
                    /*
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                     */
                }
            }

        }
    }
}
