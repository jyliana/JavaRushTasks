package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

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

public class LogParser implements IPQuery, UserQuery {
    private List<MyLog> list = new ArrayList<>();
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        this.list = getLogFormat();
    }

    private List<MyLog> getLogFormat() {
        List<String> logLines = new ArrayList<>();

        try {
            List<File> list = Files.walk(logDir)
                    .filter(Files::isRegularFile).filter(file -> file.toString().endsWith("log"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            for (File file : list) {
                logLines.addAll(Files.readAllLines(file.toPath()));
            }
        } catch (Exception e) {

        }

        for (String line : logLines) {
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

    private Set<MyLog> getDataForPeriod(final Date after, final Date before) {
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
        return getDataForPeriod(after, before).stream()
                .map(log -> log.getIp())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getUsername().equals(user))
                .map(log -> log.getIp())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(event))
                .map(log -> log.getIp())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getStatus().equals(status))
                .map(log -> log.getIp())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return list.stream()
                .map(log -> log.getUsername())
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .map(log -> log.getUsername())
                .collect(Collectors.toSet()).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getUsername().equals(user))
                .map(log -> log.getEvent())
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getIp().equals(ip))
                .map(log -> log.getUsername())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.LOGIN))
                .map(log -> log.getUsername())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                .map(log -> log.getUsername())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.WRITE_MESSAGE))
                .map(log -> log.getUsername())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.SOLVE_TASK))
                .map(log -> log.getUsername())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.SOLVE_TASK))
                .filter(log -> log.getTaskNumber() == task)
                .map(log -> log.getUsername())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.DONE_TASK))
                .map(log -> log.getUsername())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.DONE_TASK))
                .filter(log -> log.getTaskNumber() == task)
                .map(log -> log.getUsername())
                .collect(Collectors.toSet());
    }
}