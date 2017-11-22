package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://opensource.apple.com/source/vim/vim-6/vim/runtime/doc/filetype.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();

        Path tempFile = Files.createTempFile("temp-", ".tmp");
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

        String fileName = Paths.get(url.getFile()).getFileName().toString();
        /*String fileName = urlString.substring(urlString.lastIndexOf("/") + 1);*/
        Path target = downloadDirectory.resolve(fileName);
        Files.move(tempFile, target, StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();
        return target;
    }
}
