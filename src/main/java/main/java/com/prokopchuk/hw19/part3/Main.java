package main.java.com.prokopchuk.hw19.part3;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        String sourceFileName, destinationFileName;
        int lineNumber;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("write source fileName");
            sourceFileName = scanner.nextLine();
            System.out.println("write destination fileName");
            destinationFileName = scanner.nextLine();
            System.out.println("write line numbers");
            lineNumber = scanner.nextInt();

        }
        nioReaderWriter(sourceFileName, destinationFileName, lineNumber);
        //ioReaderWriter(sourceFileName, destinationFileName, lineNumber);
    }

    /**
     * Check if file contains specified number of string lines
     * @param file
     * @return true if file contains specified number of string lines or more
     */
    private static boolean checkFileIO(File file, int lineNumber) throws IOException {
        FileReader input = new FileReader(file);
        LineNumberReader count = new LineNumberReader(input);
        for (int i = 0; i < lineNumber; i++) {
            count.readLine();
        }
        return count.getLineNumber() == lineNumber;
    }

    /**
     * method to solve task using io library
     * @param sourceFileName
     * @param destinationFileName
     * @param lineNumber
     * @throws IOException
     */
    private static void ioReaderWriter(String sourceFileName,
                                       String destinationFileName,
                                       int lineNumber) throws IOException {
        File file = new File(sourceFileName);
        if (file.exists() && file.isFile() && checkFileIO(file, lineNumber)) {
            try (BufferedReader reader =
                         new BufferedReader(new FileReader(sourceFileName));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName))) {
                for (int i = 0; i < lineNumber; i++) {
                    String line = reader.readLine();
                    writer.write(line);
                    writer.newLine();
                }
            }
        } else {
            System.out.println("Incorrect data");
        }
    }

    /**
     * method to solve task using nio library
     * @throws IOException
     */
    private static void nioReaderWriter(String sourceFileName,
                                        String destinationFileName,
                                        int lineNumber) throws IOException {
        Path source = Paths.get(sourceFileName);
        if (!Files.exists(source)) {
            System.out.println("Source file not exists");
            return;
        }
        if (!Files.isRegularFile(source)) {
            System.out.println(sourceFileName + " is not a regular file");
            return;
        }
        Path dest = Paths.get(destinationFileName);
        try (Stream<String> lines = Files.lines(source, Charset.defaultCharset())) {
            List<String> list = lines.collect(Collectors.toList());
            if (list.size() > lineNumber) {
                Files.write(dest, list.subList(0, lineNumber));
            } else {
                System.out.format("File not contains %d strings", lineNumber);
            }
        }
    }

}
