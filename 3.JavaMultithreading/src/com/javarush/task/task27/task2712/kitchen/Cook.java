package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;

public class Cook extends Observable {
    private String name;
    private boolean busy;

    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public String toString() {
        return name;
    }

    /*  @Override
      public void update(Observable observable, Object arg) {
          ConsoleHelper.writeMessage("Start cooking - " + arg + ", cooking time " + ((Order) arg).getTotalCookingTime() + "min");
          StatisticManager statisticManager = StatisticManager.getInstance();
          Order order = (Order) arg;
          Tablet tablet = (Tablet) observable;
          statisticManager.register(new CookedOrderEventDataRow(tablet.toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
          setChanged();
          notifyObservers(arg);
      }*/

    public void startCookingOrder(Order order) {
        busy = true;
        StatisticManager statisticManager = StatisticManager.getInstance();
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        statisticManager.register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        statisticManager.register(this);

        try {
            Thread.sleep(order.getTotalCookingTime()*10);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        setChanged();
        notifyObservers(order);
        busy = false;
    }
}
