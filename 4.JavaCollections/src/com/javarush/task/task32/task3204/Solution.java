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
        char[] numbers = new char[10];
        char[] letters = new char[26];

        int tmp, i;
        for (tmp = 48, i = 0; i < numbers.length; i++, tmp++) {
            numbers[i] = (char) tmp;
        }
        for (tmp = 97, i = 0; i < letters.length; i++, tmp++) {
            letters[i] = (char) tmp;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        List<Object> list = new ArrayList<>();
        list.add(numbers[(int) (Math.random() * numbers.length)]);
        for (int j = 1; j < 8; j++) {
            String line = String.valueOf(letters[(int) (Math.random() * letters.length)]);
            if (j % 2 == 0) {
                list.add(line.toUpperCase());
            } else
                list.add(line);
        }
        Collections.shuffle(list);
        for (Object item : list) {
            stream.write(item.toString().getBytes());
        }
        return stream;
    }
}