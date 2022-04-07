package main.threads;

public class CombineResults {

    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            String name = "file" + i + ".txt";
            String fileName = "/tmp/" + name;

            threads[i] = new Thread(new ReadFiles(fileName, count));
            threads[i].start();
        }

        for (int i = 0; i < 3; i++) {
            threads[i].join();
        }

        System.out.println("Done running all threads from main");
        System.out.println("Count " + count.getCount());
    }

}
