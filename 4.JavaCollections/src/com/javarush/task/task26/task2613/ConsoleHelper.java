package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String line = "";
        try {
            line = bis.readLine();
            if (line.toUpperCase().equals("EXIT"))
                throw new InterruptOperationException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage("Please enter currency code:");
        String line = readString();

        while (line.length() != 3) {
            writeMessage("Code is incorrect, please enter valid currency code.");
            line = readString();
        }

        return line.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
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

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Please choose operation: 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
        int number = 0;
        try {
            number = Integer.parseInt(readString());
        } catch (NumberFormatException e) {
        }
        while (!String.valueOf(number).matches("^[1-4]{1}$")) {
            writeMessage("Incorrect data, please choose operation again: 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
            try {
                number = Integer.parseInt(readString());
            } catch (NumberFormatException e) {
            }
        }
        return Operation.getAllowableOperationByOrdinal(number);
    }
}
