package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        String cardNumber = "";
        String pin = "";

        while (true) {
            ConsoleHelper.writeMessage("Please enter card number:");
            cardNumber = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Please enter pin code:");
            pin = ConsoleHelper.readString();

            if (cardNumber.matches("^\\d{12}$") && pin.matches("^\\d{4}$")) {
                if (validCreditCards.containsKey(cardNumber) && validCreditCards.getString(cardNumber).equals(pin)) {
                    ConsoleHelper.writeMessage("Verification was successfully passed.");
                    return;
                }
            } else ConsoleHelper.writeMessage("Data is incorrect, please try again.");
        }
    }
}
