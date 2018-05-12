package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.login_en");

    @Override
    public void execute() throws InterruptOperationException {
        String cardNumber = "";
        String pin = "";
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            cardNumber = ConsoleHelper.readString();
            pin = ConsoleHelper.readString();

            if (cardNumber.matches("^\\d{12}$") && pin.matches("^\\d{4}$")) {
                if (validCreditCards.containsKey(cardNumber) && validCreditCards.getString(cardNumber).equals(pin)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));
                    return;
                }
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }
}
