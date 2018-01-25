package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet  {
    static Logger logger = Logger.getLogger(Tablet.class.getName());
    final int number;
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder() {
        try {
            Order order = new Order(this);
            refactoredOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void refactoredOrder(Order order) {
        ConsoleHelper.writeMessage(order.toString());
        if (!order.isEmpty()) {
            /*setChanged();
            notifyObservers(order);*/
            queue.add(order);
            try {
                new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            } catch (NoVideoAvailableException e) {
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
        }
    }

    public void createTestOrder() {

        try {
            TestOrder testOrder = new TestOrder(this);
            refactoredOrder(testOrder);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }


    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
