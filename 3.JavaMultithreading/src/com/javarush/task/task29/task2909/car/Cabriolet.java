package com.javarush.task.task29.task2909.car;

public class Cabriolet extends Car {
    public Cabriolet(int type, int numberOfPassengers) {
        super(type, numberOfPassengers);
    }

    public Cabriolet(int numberOfPassengers) {
        super(2, numberOfPassengers);
    }
}
