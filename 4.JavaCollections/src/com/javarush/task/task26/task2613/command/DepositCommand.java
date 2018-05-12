package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res= ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        try {
            String[] twoDigits = ConsoleHelper.getValidTwoDigits(code);
            CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
            currencyManipulator.addAmount(Integer.parseInt(twoDigits[0]), Integer.parseInt(twoDigits[1]));
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(twoDigits[0]) * Integer.parseInt(twoDigits[1]), currencyManipulator.getCurrencyCode()));

        } catch (Exception e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
