package com.javarush.task.task29.task2909.car;

public class Sedan extends Car {

    public Sedan(int type, int numberOfPassengers) {
        super(type, numberOfPassengers);
    }

    public Sedan(int numberOfPassengers) {
        super(1, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SEDAN_SPEED;
    }
}
