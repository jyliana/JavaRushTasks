package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Do you want to exit? Enter 'y' - yes or 'n' - no.");
        if (ConsoleHelper.readString().equalsIgnoreCase("y")) {
            ConsoleHelper.writeMessage("Bye!");
        }

    }
}
