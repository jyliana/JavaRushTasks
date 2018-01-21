package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

public class Restaurant {

    public static void main(String[] args) {
        Tablet tablet = new Tablet(5);
        Cook amigo = new Cook("Amigo");
        Waiter waitor = new Waiter();
        amigo.addObserver(waitor);
        tablet.addObserver(amigo);
        tablet.createOrder();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        Cook cook2=new Cook("mAmigo");
        Tablet tablet2=new Tablet(5);
        Waiter waiter2=new Waiter();
        cook2.addObserver(waiter2);
        tablet2.addObserver(cook2);
        tablet2.createOrder();
        directorTablet=new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        Cook cook3=new Cook("Am");
        Tablet tablet3=new Tablet(5);
        Waiter waiter3=new Waiter();
        cook3.addObserver(waiter3);
        tablet3.addObserver(cook3);
        tablet3.createOrder();
        directorTablet=new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
