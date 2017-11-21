package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File path = new File(root);
        List<String> list = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();
        Collections.addAll(list, path.list());

        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, currentFile.listFiles());
            } else {
                list.add(currentFile.getAbsolutePath());
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        getFileTree("d:/2/");
    }
}
