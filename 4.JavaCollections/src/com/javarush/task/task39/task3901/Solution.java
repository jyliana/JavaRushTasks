package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        Set<String> set = new HashSet<>();
        String line = "";
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            String tmp = String.valueOf(s.charAt(i));
            if (!line.contains(tmp)) {
                line += String.valueOf(s.charAt(i));
                set.add(line);
            } else line = String.valueOf(s.charAt(i));
        }

        for (String element : set) {
            if (element.length() > max)
                max = element.length();
        }
        return max;
    }
}
