package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("А роза упала на лапу Азора"));
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] line = s.toLowerCase().replace(" ", "").toCharArray();

        for (Character x : line) {
            if (!map.containsKey(x)) {
                map.put(x, 1);
            } else map.put(x, map.get(x) + 1);
        }

        int countOdd = 0;

        for (Map.Entry<Character, Integer> element : map.entrySet()) {
            if (element.getValue() % 2 != 0)
                countOdd++;
        }
        return countOdd == 0 || countOdd == 1;
    }
}