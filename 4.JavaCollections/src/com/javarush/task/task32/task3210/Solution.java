package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        byte[] b = new byte[args[2].length()];
        raf.seek(Long.parseLong(args[1]));
        raf.read(b, 0, b.length);

        String readed = new String(b);
        raf.seek(raf.length());
        if (args[2].equals(readed))
            raf.write("true".getBytes());
        else
            raf.write("false".getBytes());
        raf.close();
    }
}
