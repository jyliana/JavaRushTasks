package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {


    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();

        int len = Dish.values().length - 1;

        for (int i = 0; i < Math.random() * 10 + 1; i++) {
            dishes.add(Dish.values()[(int) (Math.random() * len)]);
        }
    }
}
