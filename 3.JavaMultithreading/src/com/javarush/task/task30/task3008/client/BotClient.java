package com.javarush.task.task30.task3008.client;

import java.io.IOException;

public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() throws IOException {
        String botName = "date_bot_" + (int) (Math.random() * 100);
        return botName;
    }

    public class BotSocketThread extends SocketThread {

    }
}
