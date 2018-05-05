package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String line = "";
        try {
            line = bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static String askCurrencyCode() {
        writeMessage("Please enter currency code:");
        String line = readString();

        while (line.length() != 3) {
            writeMessage("Code is incorrect, please enter valid currency code.");
            line = readString();
        }

        return line.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        writeMessage("Please enter 2 positive integer numbers: nominal and quantity of banknotes");
        String[] twoDigits = readString().split(" ");

        if (twoDigits.length == 2) {
            while (!twoDigits[0].matches("^[1-9]\\d*$") || !twoDigits[1].matches("^[1-9]\\d*$")) {
                writeMessage("Incorrect data, please try again.");
                twoDigits = readString().split(" ");
            }
        }
        return twoDigits;
    }

}
