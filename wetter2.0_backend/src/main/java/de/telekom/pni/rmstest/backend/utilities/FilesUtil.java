package de.telekom.pni.rmstest.backend.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FilesUtil {

    public FilesUtil() {
    }

    public String readTextFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }


    public List<String> readTextFileByLines(String fileName) throws IOException {
     //  List<String> lines = Files.readAllLines(Paths.get(fileName));
//        return lines;
        return null;
    }


    public void writeToTextFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
    }

} 