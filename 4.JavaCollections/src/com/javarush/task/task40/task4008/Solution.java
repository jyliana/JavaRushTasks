package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        String[] tmp = date.split(" ");
        String sdate = null;
        String stime = null;
        DateTimeFormatter formatter;

        if (tmp.length == 2) {
            sdate = tmp[0].contains(".") ? tmp[0] : null;
            stime = tmp[1].contains(":") ? tmp[1] : null;
        } else if (tmp.length == 1) {
            sdate = tmp[0].contains(".") ? tmp[0] : null;
            stime = tmp[0].contains(":") ? tmp[0] : null;
        }

        if (sdate != null) {
            formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate localDate = LocalDate.parse(sdate, formatter);
            System.out.println("День: " + localDate.getDayOfMonth());
            System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
            System.out.println("День месяца: " + localDate.getDayOfMonth());
            System.out.println("День года: " + localDate.getDayOfYear());
            System.out.println("Неделя месяца: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
            System.out.println("Неделя года: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
            System.out.println("Месяц: " + localDate.getMonth().getValue());
            System.out.println("Год: " + localDate.getYear());
        }
        if (stime != null) {
            formatter = DateTimeFormatter.ofPattern("H:m:s");
            LocalTime localTime = LocalTime.parse(stime, formatter);
            System.out.println("AM или PM: " + (localTime.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM"));
            System.out.println("Часы: " + localTime.get(ChronoField.HOUR_OF_AMPM));
            System.out.println("Часы дня: " + localTime.getHour());
            System.out.println("Минуты: " + localTime.getMinute());
            System.out.println("Секунды: " + localTime.getSecond());
        }
    }
}
