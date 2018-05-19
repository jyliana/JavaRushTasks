package com.javarush.task.task40.task4012;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isLeap(LocalDate.parse("01.01.2016", DateTimeFormatter.ofPattern("d.M.yyyy"))));
        LocalDateTime time = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
        LocalDate date = LocalDate.now();
        LocalDate date1 = LocalDate.from(time);
        System.out.println(isBefore(time));
        System.out.println(getPeriodBetween(date1, date));
    }

    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        LocalDateTime time = LocalDateTime.now();
        return dateTime.isBefore(time);

    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        if (firstDate.isBefore(secondDate))
            return Period.between(firstDate, secondDate);
        else
            return Period.between(secondDate, firstDate);
    }
}
