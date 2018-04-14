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
        Date after = dateFormat.parse("13.09.2030 5:04:50");

        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, new Date()));
        System.out.println("Inna " + before + " " + logParser.getIPsForUser("Inna", before, new Date()) + " " + new Date());
        System.out.println(logParser.getIPsForEvent(Event.LOGIN, before, new Date()));
        System.out.println(logParser.getIPsForStatus(Status.ERROR, before, new Date()));
        System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(null, new Date()));
        System.out.println(logParser.getNumberOfUserEvents("Inna", null, after));
        System.out.println(logParser.getUsersForIP("126.34.12.5", null, after));
        System.out.println(logParser.getLoggedUsers(before, new Date()));
        System.out.println(logParser.getDownloadedPluginUsers(null, after));
        System.out.println(logParser.getWroteMessageUsers(before, after));
        System.out.println(logParser.getSolvedTaskUsers(before, after));
        System.out.println(logParser.getSolvedTaskUsers(before, after, 17));
        System.out.println(logParser.getDoneTaskUsers(before, after));
        System.out.println(logParser.getDoneTaskUsers(null, after, 15));
        System.out.println(logParser.getDatesForUserAndEvent("Inna", Event.SOLVE_TASK, null, null));
        System.out.println(logParser.getDatesWhenSomethingFailed(null, null));
        System.out.println(logParser.getDatesWhenErrorHappened(null, null));
    }
}