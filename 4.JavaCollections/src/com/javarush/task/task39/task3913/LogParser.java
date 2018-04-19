package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
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

    private Set<MyLog> getDataForPeriod(Date after, Date before) {
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
                .filter(log -> log.getUser().equals(user))
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
                .map(log -> log.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .map(log -> log.getUser())
                .collect(Collectors.toSet()).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getUser().equals(user))
                .map(log -> log.getEvent())
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getIp().equals(ip))
                .map(log -> log.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.LOGIN))
                .map(log -> log.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                .map(log -> log.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.WRITE_MESSAGE))
                .map(log -> log.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.SOLVE_TASK))
                .map(log -> log.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.SOLVE_TASK) && log.getTaskNumber() == task)
                .map(log -> log.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.DONE_TASK))
                .map(log -> log.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.DONE_TASK) && log.getTaskNumber() == task)
                .map(log -> log.getUser())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getUser().equals(user) && log.getEvent().equals(event))
                .map(log -> log.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getStatus().equals(Status.FAILED))
                .map(log -> log.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getStatus().equals(Status.ERROR))
                .map(log -> log.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return getDatesForUserAndEvent(user, Event.LOGIN, after, before).stream()
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getUser().equals(user) && log.getEvent().equals(Event.SOLVE_TASK) && log.getTaskNumber() == task)
                .map(log -> log.getDate())
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getUser().equals(user) && log.getEvent().equals(Event.DONE_TASK) && log.getTaskNumber() == task)
                .map(log -> log.getDate())
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getUser().equals(user) && log.getEvent().equals(Event.WRITE_MESSAGE))
                .map(log -> log.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getUser().equals(user) && log.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                .map(log -> log.getDate())
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .map(log -> log.getEvent())
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .map(log -> log.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getIp().equals(ip))
                .map(log -> log.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getUser().equals(user))
                .map(log -> log.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getStatus().equals(Status.FAILED))
                .map(log -> log.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getStatus().equals(Status.ERROR))
                .map(log -> log.getEvent())
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.SOLVE_TASK) && log.getTaskNumber() == task)
                .map(log -> log.getStatus())
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.DONE_TASK) && log.getTaskNumber() == task)
                .map(log -> log.getStatus())
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.SOLVE_TASK))
                .collect(Collectors.groupingBy(MyLog::getTaskNumber, Collectors.summingInt(x -> 1)));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return getDataForPeriod(after, before).stream()
                .filter(log -> log.getEvent().equals(Event.DONE_TASK))
                .collect(Collectors.groupingBy(MyLog::getTaskNumber, Collectors.summingInt(x -> 1)));
    }

    @Override
    public Set<Object> execute(String query) {

        if (query.split(" ").length == 2) {
            String oldQueryFormat = query.split(" ")[1];
            return list.stream()
                    .map(log -> getField(log, oldQueryFormat))
                    .collect(Collectors.toSet());
        } else {
            String value1 = query.split("\"")[1];
            String field1 = query.split(" ")[1];
            String field2 = query.split(" ")[3];

            return list.stream()
                    .filter(log -> getField(log, field2).equals(convertValue(field2, value1)))
                    .map(log -> getField(log, field1))
                    .collect(Collectors.toSet());
        }
    }

    public Object getField(MyLog log, String string) {
        Class c = MyLog.class;
        try {
            Field field = c.getDeclaredField(string);
            field.setAccessible(true);
            return field.get(log);
        } catch (Exception e) {
            return null;
        }
    }

    public Object convertValue(String field, String value) {
        switch (field) {
            case "event":
                return Event.valueOf(value);
            case "status":
                return Status.valueOf(value);
            case "date":
                try {
                    return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(value);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
        }
        return value;
    }
}
