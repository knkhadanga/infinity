package main.audio;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.*;
import java.util.*;
public class Audio {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        String memoryStickPath = "/Volumes/khadanga";
        Map<String, List<Path>> filesByHash = new HashMap<>();

        try {
            Files.walkFileTree(Paths.get(memoryStickPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    if (!Files.isReadable(dir)) {
                        System.out.println("Skipping unreadable directory: " + dir);
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (isAudioFile(file.toString()) && Files.isReadable(file)) {
                        try {
                            String hash = calculateMD5(file.toFile());
                            filesByHash.computeIfAbsent(hash, k -> new ArrayList<>()).add(file);
                        } catch (IOException | NoSuchAlgorithmException e) {
                            System.err.println("Error processing file: " + file);
                            e.printStackTrace();
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.out.println("Failed to access file: " + file + " - " + exc.getMessage());
                    return FileVisitResult.CONTINUE;
                }
            });

            printDuplicates(filesByHash, Paths.get(memoryStickPath));
        } catch (IOException e) {
            System.err.println("Error accessing memory stick: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean isAudioFile(String fileName) {
        String[] audioExtensions = {".mp3", ".wav", ".ogg", ".flac", ".aac", ".wma"};
        return Arrays.stream(audioExtensions).anyMatch(ext -> fileName.toLowerCase().endsWith(ext));
    }

    private static String calculateMD5(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(file.toPath());
             DigestInputStream dis = new DigestInputStream(is, md)) {
            byte[] buffer = new byte[8192];
            while (dis.read(buffer) != -1);
        }
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static void printDuplicates(Map<String, List<Path>> filesByHash, Path rootPath) {
        System.out.println("\nDuplicate audio files found on memory stick:");
        System.out.println("==========================================");

        filesByHash.forEach((hash, files) -> {
            if (files.size() > 1) {
                System.out.println("Duplicate set:");
                files.forEach(file -> {
                    Path relativePath = rootPath.relativize(file);
                    System.out.println("  " + relativePath);
                });
                System.out.println("File size: " + formatFileSize(files.get(0).toFile().length()));
                System.out.println();
            }
        });
    }

    private static String formatFileSize(long size) {
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return String.format("%.1f %s", size / Math.pow(1024, digitGroups), units[digitGroups]);
    }
}
