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
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(orderQueue);
        Cook cook2 = new Cook("Romeo");
        cook2.setQueue(orderQueue);

        Thread cooker1 = new Thread(cook1);
        Thread cooker2 = new Thread(cook2);
        cooker1.start();
        cooker2.start();

        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        /*StatisticManager.getInstance().register(cook1);
        StatisticManager.getInstance().register(cook2);
        OrderManager orderManager = new OrderManager(orderQueue);*/

        List<Tablet> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Tablet(i));
            list.get(i).setQueue(orderQueue);
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
