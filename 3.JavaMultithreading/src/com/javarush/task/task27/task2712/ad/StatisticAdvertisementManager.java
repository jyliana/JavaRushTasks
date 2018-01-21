package com.javarush.task.task27.task2712.ad;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private static AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    public TreeMap<String, Integer> getVideoSet() {
        TreeMap<String, Integer> videoSet = new TreeMap<>(Comparator.comparing(s -> s.toLowerCase()));

        List<Advertisement> list = AdvertisementStorage.getInstance().list();
        for (Advertisement item : list) {
            videoSet.put(item.getName(), item.getHits());
        }
        return videoSet;
    }
}
