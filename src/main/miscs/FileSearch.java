package main.miscs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {
    public static void main(String[] args) throws IOException {
        new FileSearch().fileSearch("/Users/kkhadang/Desktop/Patch-OCP/24428155");
    }

    void fileSearch(String directory) throws IOException {
        List<String> output = new ArrayList<>();
        fileSearch(directory, output);

        for (String name : output) {
            System.out.println(name);
        }
    }

    void fileSearch(String path, List<String> list) throws IOException {
        File file = new File(path);
        File[] files = file.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                fileSearch(f.getPath(), list);
            }else {
                String name = f.getCanonicalPath();
                list.add(name);
            }
        }
    }
}
