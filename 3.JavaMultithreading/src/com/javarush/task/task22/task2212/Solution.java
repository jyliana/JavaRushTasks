package com.javarush.task.task22.task2212;

import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.length() < 10 || telNumber.length() > 15)
            return false;
        return Pattern.compile("(\\+\\d{2})?((\\(\\d{3}\\))|(\\d{3}))\\d{3}-?\\d{2}-?\\d{2}").matcher(telNumber).matches();
    }

    public static void main(String[] args) {

    }
}
