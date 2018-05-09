package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class LoginCommand implements Command {
    private String cardNumber = "123456789012";
    private String pin = "1234";

    @Override
    public void execute() throws InterruptOperationException {
        String number = "";
        String pincode = "";

        while (true) {
            ConsoleHelper.writeMessage("Please enter card number:");
            number = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Please enter pin code:");
            pincode = ConsoleHelper.readString();

            if (number.matches("^\\d{12}$") && pincode.matches("^\\d{4}$")) {
                if (number.equals(cardNumber) && pincode.equals(pin)) {
                    ConsoleHelper.writeMessage("Verification was successfully passed.");
                    return;
                }
            } else ConsoleHelper.writeMessage("Data is incorrect, please try again.");
        }
    }
}
