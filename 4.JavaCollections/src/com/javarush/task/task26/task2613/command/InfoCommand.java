package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

class InfoCommand implements Command {
    @Override
    public void execute() {
        boolean money = false;
        for (CurrencyManipulator item : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (item.hasMoney()) {
                ConsoleHelper.writeMessage(item.getCurrencyCode() + " - " + item.getTotalAmount());
                money = true;
            }
        }
        if (!money) {
            ConsoleHelper.writeMessage("No money available.");
        }
    }
}
