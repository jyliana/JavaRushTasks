package com.javarush.task.task31.task3110;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(zipFile));
             InputStream in = Files.newInputStream(source)) {
            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            out.putNextEntry(zipEntry);

            byte[] buffer = new byte[1000];
            int tmp;
            while ((tmp = in.read(buffer)) > 0) {
                out.write(buffer, 0, tmp);
            }
            out.closeEntry();
        }
    }
}
