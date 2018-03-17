package com.javarush.task.task36.task3605;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(new File(args[0]));
        TreeSet set = new TreeSet();
        while (fileReader.ready()) {
            String line = "" + (char) fileReader.read();
            if (line.matches(("[a-zA-Z]"))) {
                set.add(line.toLowerCase());
            }
        }
        int length = 5;
        if (set.size() < length)
            length = set.size();

        for (int i = 0; i < length; i++) {
            System.out.print(set.pollFirst());
        }
    }
}
