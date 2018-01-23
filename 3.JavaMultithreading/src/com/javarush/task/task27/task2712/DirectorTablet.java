package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class DirectorTablet {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    public void printAdvertisementProfit() {
        Map<Date, Long> map = StatisticManager.getInstance().getAdvertisementProfit();
        double total = 0;

        for (Map.Entry<Date, Long> item : map.entrySet()) {
            double value = item.getValue();
            ConsoleHelper.writeMessage(String.format("%s - %.2f", sdf.format(item.getKey()), value / 100));
            total += value;
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", total / 100));
        ConsoleHelper.writeMessage("");
    }

    public void printCookWorkloading() {
        TreeMap<Date, TreeMap<String, Long>> map = StatisticManager.getInstance().getCookWorkloading();

        for (Map.Entry<Date, TreeMap<String, Long>> entry : map.entrySet()) {

            ConsoleHelper.writeMessage(sdf.format(entry.getKey()));

            for (Map.Entry<String, Long> item : entry.getValue().entrySet()) {
                if (item.getValue() != 0) {
                    ConsoleHelper.writeMessage(item.getKey() + " - " + Math.round(Math.ceil(item.getValue() / 1.0f)) + " min");
                }
            }
            ConsoleHelper.writeMessage("");
        }

    }

    public void printActiveVideoSet() {
        TreeMap<String, Integer> map = StatisticAdvertisementManager.getInstance().getVideoSet();

        for (Map.Entry<String, Integer> item : map.entrySet()) {
            if (item.getValue() > 0)
                ConsoleHelper.writeMessage(item.getKey() + " - " + item.getValue());
        }
    }

    public void printArchivedVideoSet() {
        TreeMap<String, Integer> map = StatisticAdvertisementManager.getInstance().getVideoSet();

        for (Map.Entry<String, Integer> item : map.entrySet()) {
            if (item.getValue() <= 0)
                ConsoleHelper.writeMessage(item.getKey());
        }
    }
}
