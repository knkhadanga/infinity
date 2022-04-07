package main.threads;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utility {

    public static void main(String[] args) throws IOException {
        File file = new File("/tmp/file1.txt");
        FileWriter fr = new FileWriter(file);

        while (true) {
            writeUsingFileWriter(fr);
            Path path = Paths.get("/tmp/file1.txt");
            long bytes = Files.size(path);
            if ((bytes / (1024 * 1024 * 1024) >= 0.3)) {
                break;
            }
        }

        fr.close();
    }

    private static void writeUsingFileWriter(FileWriter fr) throws IOException {


        for (int i = 0; i < 1000; i++) {
            fr.write("hello world ... dummy line\n");
        }
        fr.write("500 Error\n");

    }
}
