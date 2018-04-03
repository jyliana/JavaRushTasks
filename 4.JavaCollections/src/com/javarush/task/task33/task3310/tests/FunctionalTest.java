package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        String line1 = "Hi!";
        String line2 = "Goodbye!";
        String line3 = "Hi!";

        Long id1 = shortener.getId(line1);
        Long id2 = shortener.getId(line2);
        Long id3 = shortener.getId(line3);

        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);

        String newLine1 = shortener.getString(id1);
        String newLine2 = shortener.getString(id2);
        String newLine3 = shortener.getString(id3);

        Assert.assertEquals(line1, newLine1);
        Assert.assertEquals(line2, newLine2);
        Assert.assertEquals(line3, newLine3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}

