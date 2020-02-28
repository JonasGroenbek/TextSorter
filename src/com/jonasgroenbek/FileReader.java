package com.jonasgroenbek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class FileReader {

    public static List readFile(Path path){
        try {
            return Files.lines(path)
                    .map(str -> str
                            .toLowerCase()
                            .replaceAll("[^a-z ]", "")
                            .split(" "))
                    .flatMap(Arrays::stream)
                    .filter(word -> !word.isBlank())
                    .collect(Collectors.toList());
        } catch(IOException e){
            System.out.println(e.getStackTrace());
            System.exit(0);
        }
        return null;
    }
}
