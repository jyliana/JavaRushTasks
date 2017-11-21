package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File renamedFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        List<File> list = new ArrayList();

        list = fileTree(path, list);

        if (list.size() > 0) {
            list.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            FileUtils.renameFile(resultFileAbsolutePath, renamedFile);

            FileOutputStream out = new FileOutputStream(renamedFile);
            FileInputStream tmp;
            for (File file : list) {
                tmp = new FileInputStream(file);
                while (tmp.available() > 0) {
                    out.write(tmp.read());
                }
                out.write("\n".getBytes());
            }
            out.close();
        }
    }

    public static List<File> fileTree(File path, List<File> list) {
        for (File file : path.listFiles()) {
            if (file.isFile()) {
                if (file.length() > 50)
                    FileUtils.deleteFile(file);
                else
                    list.add(file);
            } else if (file.isDirectory()) {
                list = fileTree(file, list);
            }
        }
        return list;
    }
}
