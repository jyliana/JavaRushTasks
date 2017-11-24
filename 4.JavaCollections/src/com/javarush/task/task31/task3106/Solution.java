package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Arrays.sort(args, 1, args.length);
        List<InputStream> list = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            list.add(new FileInputStream(args[i]));
        }

        ZipInputStream zipIn = new ZipInputStream(new SequenceInputStream(Collections.enumeration(list)));
        FileOutputStream fileOutStream = new FileOutputStream(args[0]);

        byte[] buf = new byte[1000];
        while (zipIn.getNextEntry() != null) {
            int count;
            while ((count = zipIn.read(buf)) != -1) {
                fileOutStream.write(buf, 0, count);
            }
        }
        zipIn.close();
        fileOutStream.close();
    }
}
