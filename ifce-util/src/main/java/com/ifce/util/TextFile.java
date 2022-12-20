package com.ifce.util;

import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Read/write on file
 */
public class TextFile {
    /**
     * Write in file
     *
     * @param filePath file path
     * @param text     text
     */
    public static void write(
            final String filePath,
            final String text
    ) {
        try {
            Files.write(Paths.get(filePath), text.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static String read(final String filePath) {
        try {
            val stringBuilder = new StringBuilder();
            Files.lines(Paths.get(filePath)).forEach(i -> stringBuilder.append(i).append("\n"));
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        //write("tt", "ап");
        System.out.println(read("tt"));
    }
}