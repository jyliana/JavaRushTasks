package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public Map<Date, Long> getAdvertisementProfit() {
        Map<Date, Long> map = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> list = statisticStorage.get(EventType.SELECTED_VIDEOS);
        Calendar calendar;
        Date date;

        for (EventDataRow event : list) {
            VideoSelectedEventDataRow video = (VideoSelectedEventDataRow) event;

            calendar = Calendar.getInstance();
            calendar.setTime(video.getDate());
            GregorianCalendar gc = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            date = gc.getTime();

            long amount = video.getAmount();
            if (map.containsKey(date)) {
                amount += map.get(date);
            }
            map.put(date, amount);
        }
        return map;
    }

    public TreeMap<Date, TreeMap<String, Long>> getCookWorkloading() {
        List<EventDataRow> list = statisticStorage.get(EventType.COOKED_ORDER);
        TreeMap<Date, TreeMap<String, Long>> map = new TreeMap<>(Collections.<Date>reverseOrder());

        Date date;
        Calendar calendar;
        String cookName;
        long cookingTimeSeconds;

        for (EventDataRow event : list) {
            CookedOrderEventDataRow cookedOrder = (CookedOrderEventDataRow) event;

            calendar = Calendar.getInstance();
            calendar.setTime(cookedOrder.getDate());
            GregorianCalendar gc = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            date = gc.getTime();

            cookName = cookedOrder.getCookName();
            cookingTimeSeconds = cookedOrder.getTime();

            TreeMap<String, Long> cookTime = new TreeMap<>();
            if (map.containsKey(date)) {
                cookTime = map.get(date);
                if (cookTime.containsKey(cookName)) {
                    cookingTimeSeconds += cookTime.get(cookName);
                }
            }
            cookTime.put(cookName, cookingTimeSeconds);
            map.put(date, cookTime);
        }
        return map;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            if (data != null)
                storage.get(data.getType()).add(data);
        }

        private List<EventDataRow> get(EventType selectedVideos) {
            return storage.get(selectedVideos);
        }
    }

}
