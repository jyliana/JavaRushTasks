package com.javarush.task.task39.task3913;

import java.util.Date;

public class MyLog {
    private String ip;
    private String user;
    private Date date;
    private Event event;
    private int taskNumber;
    private Status status;

    public MyLog(String ip, String user, Date date, Event event, int taskNumber, Status status) {
        this.ip = ip;
        this.user = user;
        this.date = date;
        this.event = event;
        this.taskNumber = taskNumber;
        this.status = status;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getIp() {
        return ip;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public Status getStatus() {
        return status;
    }

}
