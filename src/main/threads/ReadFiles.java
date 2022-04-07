package main.threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFiles implements Runnable {

    String fileName;
    Count count;

    public ReadFiles(String fileName, Count count) {
        this.fileName = fileName;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("Started reading file - " + fileName);
        try {
            readFile(fileName, count);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Fail to process file - " + fileName);
        }
    }

    void readFile(String fileName, Count count) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File doesn't exist - " + fileName);
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String currentLine = scanner.nextLine();
            if (currentLine.contains("500")) {
                count.incrementCount();
            }
        }
    }

    public static void main(String[] args) {

    }
}
