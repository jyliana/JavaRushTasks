package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path fileName = Paths.get(args[0]);
        ZipInputStream inputZip = new ZipInputStream(new FileInputStream(args[1]));
        Map<String, ByteArrayOutputStream> map = new HashMap<>();
        ZipEntry entry;
        while ((entry = inputZip.getNextEntry()) != null) {
            if (entry.getName().equals("new/" + fileName.getFileName()))
                continue;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count;
            while ((count = inputZip.read(buffer)) > 0)
                byteArrayOutputStream.write(buffer, 0, count);
            map.put(entry.getName(), byteArrayOutputStream);
        }
        inputZip.close();

        ZipOutputStream outputZip = new ZipOutputStream(new FileOutputStream(args[1]));
        for (Map.Entry<String, ByteArrayOutputStream> line : map.entrySet()) {
            entry = new ZipEntry(line.getKey());
            outputZip.putNextEntry(entry);
            outputZip.write(line.getValue().toByteArray());
        }
        outputZip.putNextEntry(new ZipEntry("new/" + fileName.getFileName()));
        Files.copy(fileName, outputZip);
        outputZip.close();
    }
}