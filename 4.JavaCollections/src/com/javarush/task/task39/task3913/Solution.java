package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) throws ParseException {
        LogParser logParser = new LogParser(Paths.get("d:/logs/"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date before = dateFormat.parse("13.09.2013 5:04:50");

        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, new Date()));
        System.out.println("Inna " + before + " " + logParser.getIPsForUser("Inna", before, new Date()) + " " + new Date());
        System.out.println(logParser.getIPsForEvent(Event.LOGIN, before, new Date()));
        System.out.println(logParser.getIPsForStatus(Status.ERROR, before, new Date()));
    }
}