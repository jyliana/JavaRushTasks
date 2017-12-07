package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.List;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static void main(String[] args) {
        Car car = new Car();
    }

    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            String[] tmp_wheels = loadWheelNamesFromDB();
            wheels = new ArrayList<>();
            if (tmp_wheels.length != 4)
                throw new IllegalArgumentException();
            else {
                for (int i = 0; i < tmp_wheels.length; i++) {
                    wheels.add(Wheel.valueOf(tmp_wheels[i]));
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }
}
