package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());
        ConsoleHelper.writeMessage("Server was started.");
        try {
            while (true)
                new Handler(serverSocket.accept()).start();
        } catch (Exception e) {
            System.out.println("Something wrong.");
        } finally {
            serverSocket.close();
        }
    }

    static public void sendBroadcastMessage(Message message) {
        try {
            for (Map.Entry<String, Connection> connection : connectionMap.entrySet())
                connection.getValue().send(message);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Message cannot be delivered.");
        }
    }


    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME)
                    if (!answer.getData().isEmpty() && !connectionMap.containsKey(answer.getData())) {
                        connectionMap.put(answer.getData(), connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return answer.getData();
                    }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String user : connectionMap.keySet()) {
                if (!user.equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, user));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message != null && message.getType() == MessageType.TEXT)
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                else ConsoleHelper.writeMessage("Error!");
            }
        }
    }
}
