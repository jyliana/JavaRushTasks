package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String line = "";
        try {
            line = bis.readLine();
            if (line.toUpperCase().equals("EXIT")) {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String line = readString();

        while (line.length() != 3) {
            writeMessage(res.getString("invalid.data"));
            line = readString();
        }
        return line.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] twoDigits = readString().split(" ");

        if (twoDigits.length == 2) {
            while (!twoDigits[0].matches("^[1-9]\\d*$") || !twoDigits[1].matches("^[1-9]\\d*$")) {
                writeMessage(res.getString("invalid.data"));
                twoDigits = readString().split(" ");
            }
        }
        return twoDigits;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        writeMessage("1 - " + res.getString("operation.INFO"));
        writeMessage("2 - " + res.getString("operation.DEPOSIT"));
        writeMessage("3 - " + res.getString("operation.WITHDRAW"));
        writeMessage("4 - " + res.getString("operation.EXIT"));
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
