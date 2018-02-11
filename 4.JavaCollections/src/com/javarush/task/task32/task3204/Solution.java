package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        List<Object> list = new ArrayList<>();
        int min = 97, max = 122;

        for (int j = 0; j < 8; j++) {
            String line = String.valueOf((char) (min + (Math.random() * (max - min))));
            if (j % 2 == 0) {
                list.add(line.toUpperCase());
            } else if (j % 3 == 0)
                list.add((int) (Math.random() * 10));
            else
                list.add(line);
        }
        Collections.shuffle(list);

        for (Object item : list) {
            stream.write(item.toString().getBytes());
        }
        return stream;
    }
}