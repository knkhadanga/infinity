package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessRun {

    public static void main(String[] args) throws Exception {
        // Create a ProcessBuilder object with the Python script path and arguments
        List<String> command = new ArrayList<>();
        String profileFile = "/tmp/knk/profiles/lcmflow_zfs.json";
        command.add("python"); // Replace with the actual Python interpreter path if needed
        command.add("/tmp/knk/python/backup.py"); // Replace with your script's path
        // Add any necessary arguments to the command list
        command.add("-a");
        command.add("create");
        command.add("-p");
        command.add(profileFile);
        command.add("-N");
        command.add("test_snapshot");

        ProcessBuilder processBuilder = new ProcessBuilder(command);

        // Start the process
        Process process = processBuilder.start();

        // Read the standard output and error streams
        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String line;
        while ((line = stdoutReader.readLine()) != null) {
            System.out.println(line);
        }

        while ((line = stderrReader.readLine()) != null) {
            System.err.println(line);
        }

        // Wait for the process to finish and get the exit code
        int exitCode = process.waitFor();

        // Check the exit code and handle accordingly
        if (exitCode == 0) {
            System.out.println("Python script executed successfully.");
        } else {
            System.err.println("Python script exited with error code: " + exitCode);
        }
    }
}
