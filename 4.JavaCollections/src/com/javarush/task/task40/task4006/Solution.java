package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://romanticlib.org.ua/news"));
    }

    public static void getSite(URL url) {
        try {
            Socket socket = new Socket(url.getHost(), 80);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            out.println("GET " + url.getFile());
            out.flush();

            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String answer;

            while ((answer = in.readLine()) != null) {
                System.out.println(answer);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}