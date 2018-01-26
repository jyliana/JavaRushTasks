package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            String tmp = args[0].replaceAll("[A-Za-z0-9]", "");
            if (tmp.length() != 0) {
                System.out.println("incorrect");
            } else {
                for (int i = 2; i <= 36; i++) {
                    try {
                        new BigInteger(args[0], i);
                        System.out.println(i);
                        break;
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}