package com.alevel.prokopchuk.hw19.part1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        //doRecursion();
        doIteration();
    }

    private static void doRecursion() {
        try (FileWriter writer = new FileWriter("test.txt")) {
            File directory = new File(getDirectoryPath());
                if (directory.exists() && directory.isDirectory()) {
                writeAllFilesRecursion(writer, directory, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doIteration() {
        try (FileWriter writer = new FileWriter("test2.txt")) {
            File directory = new File(getDirectoryPath());
            if (directory.exists() && directory.isDirectory()) {
                writeAllFilesIteration(writer, directory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeAllFilesRecursion(FileWriter writer, File directory, int spacesCount)
            throws IOException {
        for (File file : directory.listFiles()) {
            if (file.isDirectory() && file.listFiles() != null) {
                writeAllFilesRecursion(writer, file, spacesCount + 1);
            } else {
                writer.write(generateBlankString(spacesCount) +
                        file.getName() + " : " + dateFormat.format(file.lastModified())
                + System.lineSeparator());
            }
        }
    }


    private static void writeAllFilesIteration (FileWriter writer, File directory) throws IOException {
        if (directory.exists() && directory.isDirectory() && directory.listFiles() != null) {
            int directoryNestedLevel = nestedLevelFile(directory);
            int fileNestedLevel;
            Deque<File> deque = new ArrayDeque<>();
            deque.add(directory);
            while (!deque.isEmpty()) {
                File subDirectory = deque.pollLast();
                File[] listFiles = subDirectory.listFiles();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        if (file.isDirectory()) {
                            deque.offerLast(file);
                        } else {
                            fileNestedLevel = nestedLevelFile(file);
                            writer.write(generateBlankString(fileNestedLevel - directoryNestedLevel - 1)
                                    + file.getName() + ":" + dateFormat.format(file.lastModified())
                                    + System.lineSeparator());
                        }
                    }
                }
            }
        }
    }

    /**
     * reads directory path name from console
     * @return directory path name
     */
    private static String getDirectoryPath() {
        System.out.println("input te directory path");
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.next();
        }
    }

    /**
     * method to generate string which consists of special numbers
     * of spaces
     * @param length number of spaces
     */
    private static String generateBlankString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(' ');
        }
        return result.toString();
    }

    /**
     * count the number path separator characters to find
     * the nesting level of file
     * @param file
     * @return
     */
    private static int nestedLevelFile(File file) {
        int nestedLevel = 0;
        int lastIndex = 0;
        String filePath = file.getPath();
        String pathseparator = File.separator;
        while(lastIndex != -1){
            lastIndex = filePath.indexOf(pathseparator, lastIndex);
            if(lastIndex != -1){
                nestedLevel ++;
                lastIndex += pathseparator.length();
            }
        }
        return nestedLevel;
    }
}
