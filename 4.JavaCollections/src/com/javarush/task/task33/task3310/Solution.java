package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        /*testStrategy(new FileStorageStrategy(), 100);*/
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String item : strings) {
            set.add(shortener.getId(item));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long key : keys) {
            set.add(shortener.getString(key));
        }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> strings = new HashSet<>();

        for (long i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date dateGetIdsStart = new Date();
        Set<Long> ids = getIds(shortener, strings);
        Date dateGetIdsStop = new Date();
        long time = dateGetIdsStop.getTime() - dateGetIdsStart.getTime();
        Helper.printMessage(String.valueOf(time));

        Date dateGetStringsStart = new Date();
        Set<String> newStrings = getStrings(shortener, ids);
        Date dateGetStringsStop = new Date();
        time = dateGetStringsStop.getTime() - dateGetStringsStart.getTime();
        Helper.printMessage(String.valueOf(time));

        if (strings.equals(newStrings))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
}
