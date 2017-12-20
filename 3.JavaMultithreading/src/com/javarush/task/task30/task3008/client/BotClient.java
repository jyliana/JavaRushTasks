package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);

            if (message != null && message.contains(":")) {
                Calendar calendar = new GregorianCalendar();
                Date date = calendar.getTime();
                SimpleDateFormat dateFormat = null;
                String[] line = message.split(": ");

                switch (line[1]) {
                    case "дата": {
                        dateFormat = new SimpleDateFormat("d.MM.yyyy");
                        break;
                    }
                    case "день": {
                        dateFormat = new SimpleDateFormat("d");
                        break;
                    }
                    case "месяц": {
                        dateFormat = new SimpleDateFormat("MMMM");
                        break;
                    }
                    case "год": {
                        dateFormat = new SimpleDateFormat("YYYY");
                        break;
                    }
                    case "время": {
                        dateFormat = new SimpleDateFormat("H:mm:ss");
                        break;
                    }
                    case "час": {
                        dateFormat = new SimpleDateFormat("H");
                        break;
                    }
                    case "минуты": {
                        dateFormat = new SimpleDateFormat("m");
                        break;
                    }
                    case "секунды": {
                        dateFormat = new SimpleDateFormat("s");
                        break;
                    }
                }
                if (dateFormat != null) {
                    sendTextMessage("Информация для " + line[0] + ": " + dateFormat.format(date).toString());
                }
            }
        }
    }
}
