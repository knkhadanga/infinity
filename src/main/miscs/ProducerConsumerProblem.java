package main.miscs;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerProblem {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new LinkedList<>();

        Producer p1 = new Producer(list);
        Consumer c1 = new Consumer(list);

        Thread t1 = new Thread(p1, "Producer");
        Thread t2 = new Thread(c1, "Consumer");

        t1.start();
        t2.start();

        //wait main program until t1 and t2 are completed
        t1.join();
        t2.join();
    }

}

class Producer implements Runnable {
    List<Integer> resource;

    Producer(List input) {
        resource = input;
    }


    public void run() {
        System.out.println("Started thread - " + Thread.currentThread().getName());
        synchronized (resource) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread - " + Thread.currentThread().getName() + " writing - " + i);
                resource.add(i);
                try {
                    resource.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                resource.notifyAll();
            }
        }
    }
}

class Consumer implements Runnable {
    List<Integer> resource;

    Consumer(List input) {
        resource = input;
    }

    @Override
    public void run() {
        System.out.println("Started thread - " + Thread.currentThread().getName());
        synchronized (resource) {
            while (true) {
                while (resource.size() == 0) {
                    System.out.println("Thread - " + Thread.currentThread().getName() + " is waiting for resource");
                    try {
                        resource.wait(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int get = resource.remove(0);
                System.out.println("Thread - " + Thread.currentThread().getName() + " reading - " + get);

                if (resource.size() == 0)
                    resource.notifyAll();
            }
        }
    }

}