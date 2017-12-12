package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    static final int MAX_TRUCK_SPEED = 80;
    static final int MAX_CABRIOLET_SPEED = 90;
    static final int MAX_SEDAN_SPEED = 120;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    double fuel;
    private int type;
    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public static Car create(int type, int numberOfPassengers) {
        Car car;
        switch (type) {
            case TRUCK:
                car = new Truck(type, numberOfPassengers);
                break;
            case SEDAN:
                car = new Sedan(type, numberOfPassengers);
                break;
            case CABRIOLET:
                car = new Cabriolet(type, numberOfPassengers);
                break;
            default:
                car = null;
        }
        return car;
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }

    public double getWinterConsumption(int length) {
        return winterFuelConsumption * length + winterWarmingUp;
    }

    public double getSummerConsumption(int length) {
        return summerFuelConsumption * length;
    }

    public boolean isSummer(Date date, Date summerStart, Date summerEnd) {
        return date.before(summerEnd) && date.after(summerStart);
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (isSummer(date, SummerStart, SummerEnd))
            consumption = getSummerConsumption(length);
        else
            consumption = getWinterConsumption(length);
        return consumption;
    }

    private boolean canPassengersBeTransferred() {
        return (driverAvailable && fuel > 0) ? true : false;
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (canPassengersBeTransferred())
            return numberOfPassengers;
        else return 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0)
            fastenPassengersBelts();
        fastenDriverBelt();
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed();
}