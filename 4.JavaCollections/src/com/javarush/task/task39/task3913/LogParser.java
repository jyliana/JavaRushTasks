package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LogParser implements IPQuery {
    private List<MyLog> list = new ArrayList<>();
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        this.list = getLogFormat();
    }

    private List<String> allLogLines() {
        List<String> lines = new ArrayList<>();
        try {
            List<File> list = Files.walk(logDir)
                    .filter(Files::isRegularFile).filter(file -> file.toString().endsWith("log"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            for (File file : list) {
                lines.addAll(Files.readAllLines(file.toPath()));
            }
        } catch (Exception e) {

        }
        return lines;
    }

    private List<MyLog> getLogFormat() {
        List<String> lines = allLogLines();
        for (String line : lines) {
            String[] logData = line.split("\t");
            String ip = logData[0];
            String user = logData[1];
            Event event;
            int taskNumber = 0;

            Date date = null;
            try {
                date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(logData[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String[] tmp_event = logData[3].split(" ");
            if (tmp_event.length != 1) {
                event = Event.valueOf(tmp_event[0]);
                taskNumber = Integer.parseInt(tmp_event[1]);
            } else
                event = Event.valueOf(logData[3]);
            Status status = Status.valueOf(logData[4]);

            list.add(new MyLog(ip, user, date, event, taskNumber, status));
        }
        return list;
    }

    private Set<MyLog> getLinesForDates(final Date after, final Date before) {
        return list.stream()
                .filter(log -> log.getDate().getTime() >= (after == null ? 0 : after.getTime())
                        && log.getDate().getTime() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getLinesForDates(after, before).stream()
                .map(log -> log.getIp())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getLinesForDates(after, before).stream()
                .filter(log -> log.getUsername().equals(user))
                .map(log -> log.getIp())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getLinesForDates(after, before).stream()
                .filter(log -> log.getEvent().equals(event))
                .map(log -> log.getIp())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getLinesForDates(after, before).stream()
                .filter(log -> log.getStatus().equals(status))
                .map(log -> log.getIp())
                .collect(Collectors.toSet());
    }
}