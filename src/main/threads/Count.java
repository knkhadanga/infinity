package main.threads;

public class Count {

    double count = 0;

    public synchronized void incrementCount() {
        count++;
    }

    public double getCount() {
        return count;
    }
}
