package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Set<Cook> cooks = StatisticManager.getInstance().getCooks();
                while (true) {
                    for (Cook cook : cooks) {
                        if (!orderQueue.isEmpty() && !cook.isBusy()) {
                            Order order = orderQueue.poll();
                            if (order != null)
                                cook.startCookingOrder(order);

                        } else try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void update(Observable o, Object order) {
        orderQueue.offer((Order) order);
    }
}
