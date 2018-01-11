package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        String result = "";
        if (a > b) {
            while (a >= b) {
                result = result + a + " ";
                a--;
            }
            return result.trim();//a + " " + getAllNumbersBetween(a - 1, b);
        } else {
            if (a == b) {
                return Integer.toString(a);
            }
            while (a <= b) {
                result = result + a + " ";
                a++;
            }
            return result.trim();//a + " " + getAllNumbersBetween(a + 1, b);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}