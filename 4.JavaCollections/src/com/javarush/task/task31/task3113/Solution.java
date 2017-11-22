package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {

        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(reader.readLine());
        reader.close();
        if (!Files.isDirectory(path)) {
            System.out.println(path + " - не папка");
        } else {
            Files.walkFileTree(path, searchFileVisitor);
            System.out.println("Всего папок - " + searchFileVisitor.count_dirs);
            System.out.println("Всего файлов - " + searchFileVisitor.count_files);
            System.out.println("Общий размер - " + searchFileVisitor.count_bytes);
        }
    }

    public static class SearchFileVisitor extends SimpleFileVisitor<Path> {

        private int count_dirs = -1;
        private int count_files = 0;
        private int count_bytes = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            count_bytes += attrs.size();
            count_files++;
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            count_dirs++;
            return super.postVisitDirectory(dir, exc);
        }
    }
}
