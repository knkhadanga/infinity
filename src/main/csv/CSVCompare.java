package main.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSVCompare {

    public static void main(String[] args) {
        String file1Path = "/Users/kkhadang/Downloads/commercial_v3_km_list.csv"; // Replace with the path to the first CSV file
        String file2Path = "/Users/kkhadang/Downloads/Maintenances.csv"; // Replace with the path to the second CSV file
        String column1Name = "SYSTEMNAME"; // Replace with the column name in the first CSV file
        String column2Name = "\"Target Environment\""; // Replace with the column name in the second CSV file

        try {
            Set<String> allPodsData = getColumnData(file1Path, column1Name);
            Set<String> filterRegionData = getColumnData(file2Path, column2Name);

            // Check for matches
            allPodsData.retainAll(filterRegionData);
            System.out.println("After filter, number of pods = " + allPodsData.size());

            if (allPodsData.isEmpty()) {
                System.out.println("No matching entries found.");
            } else {
                System.out.println("Refresh had run on:");
                allPodsData.forEach(System.out::println);
                System.out.printf("Getting refresh data\n\n\n");
            }

            List<String> results = new ArrayList<>();
            allPodsData.stream().forEach(podName -> {
                try {
                    getData(file2Path, podName, results);
                } catch (IOException ex) {
                }
            });
            if (results.size() > 0) {
                System.out.printf("Total refresh count - " + results.size() + " \n\n");
                results.stream().forEach(System.out::println);
            } else {
                System.out.printf("No results");
            }
        } catch (IOException e) {
            System.err.println("Error reading the files: " + e.getMessage());
        }
    }

    public static Set<String> getColumnData(String filePath, String columnName) throws IOException {
        Set<String> columnData = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IOException("CSV file is empty.");
            }
            System.out.println("====================================");
            // Split header to get column index
            String[] headers = headerLine.split(",");
            int columnIndex = -1;
            for (int i = 0; i < headers.length; i++) {
                //  System.out.println(" > "+ headers[i].trim() + " " + columnName);
                if (headers[i].trim().equalsIgnoreCase(columnName)) {
                    columnIndex = i;
                    break;
                }
            }

            if (columnIndex == -1) {
                columnIndex = 0;
                // throw new IOException("Column '" + columnName + "' not found in the CSV file.");
            }

            // Read and parse each line
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > columnIndex) {
                    columnData.add(values[columnIndex].trim());
                }
            }
        }
        return columnData;
    }

    public static void getData(String filePath, String podName, List<String> result) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IOException("CSV file is empty.");
            }

            int columnIndex = 0; //first column

            // Read and parse each line
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (podName.equalsIgnoreCase(values[columnIndex])) {
                    result.add(line);
                }
            }
        }

    }
}
