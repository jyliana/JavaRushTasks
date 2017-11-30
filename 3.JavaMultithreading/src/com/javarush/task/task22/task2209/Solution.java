package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader = new BufferedReader(new FileReader(reader.readLine()));

        StringBuilder stringBuilder = new StringBuilder();
        while (reader.ready()) {
            stringBuilder.append(reader.readLine());
        }
        reader.close();
        String[] words = stringBuilder.toString().split(" ");

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder line = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, words);
        Collections.sort(list);
        if (list.size() > 0) {
            String tmp_line;
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).charAt(list.get(i).length() - 1) == list.get(j).toLowerCase().charAt(0)) {
                        tmp_line = list.get(i + 1);
                        list.set(i + 1, list.get(j));
                        list.set(j, tmp_line);
                    }
                }
            }
        }
        for (String item : list) {
            line.append(item + " ");

        }
        return line;
    }
}

