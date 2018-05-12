package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        /*String code = ConsoleHelper.askCurrencyCode();
        String[] twoDigits = ConsoleHelper.getValidTwoDigits(code);
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        currencyManipulator.addAmount(Integer.parseInt(twoDigits[0]), Integer.parseInt(twoDigits[1]));
        System.out.println(currencyManipulator.getTotalAmount());*/
        /*System.out.println(ConsoleHelper.askOperation());*/
        try {
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (!operation.equals(Operation.EXIT));
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
