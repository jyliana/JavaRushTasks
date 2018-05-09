package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        while (true) {
            ConsoleHelper.writeMessage("Please enter how much money you want to withdraw:");
            String amount = ConsoleHelper.readString();
            while (!amount.matches("^[1-9]\\d*$") || !currencyManipulator.isAmountAvailable(Integer.parseInt(amount))) {
                ConsoleHelper.writeMessage("Incorrect data, please try again.");
                amount = ConsoleHelper.readString();
            }
            try {
                Map<Integer, Integer> result = currencyManipulator.withdrawAmount(Integer.parseInt(amount));
                result.entrySet().stream()
                        .forEach(n -> System.out.println(n.getKey() + " - " + n.getValue()));
                break;
            } catch (NotEnoughMoneyException e) {
                System.out.println("Not enough money. Please try other amount of money.");
            }
        }
    }
}
