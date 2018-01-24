package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage instance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "четвертое видео", 400, 2, 10 * 60));
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "пятое видео", 100, 2, 10 * 60));
        add(new Advertisement(someContent, "Пятнадцатое видео", 100, 2, 10 * 60));
        add(new Advertisement(someContent, "second Video", 100, 1, 15 * 60));
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
    }

    public static AdvertisementStorage getInstance() {
        return instance;
    }


    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }

}