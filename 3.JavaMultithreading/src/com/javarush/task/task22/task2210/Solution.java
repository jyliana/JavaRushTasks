package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        String[] array = new String[tokenizer.countTokens()];

        for (int i = 0; i < array.length; i++) {
            if (tokenizer.hasMoreTokens())
                array[i] = tokenizer.nextToken();
        }
        return array;
    }
}
