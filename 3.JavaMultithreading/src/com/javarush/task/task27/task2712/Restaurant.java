package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {

        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Romeo");

        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        StatisticManager.getInstance().register(cook1);
        StatisticManager.getInstance().register(cook2);
        OrderManager orderManager = new OrderManager();

        List<Tablet> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Tablet(i));
            list.get(i).addObserver(orderManager);
        }

        Thread task = new Thread(new RandomOrderGeneratorTask(list, ORDER_CREATING_INTERVAL));
        task.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException nothing) {

        }
        task.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();
    }
}
