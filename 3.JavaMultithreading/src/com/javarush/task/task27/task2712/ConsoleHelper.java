package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    static private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> list = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        writeMessage("Пожалуйсте, выберите блюда.");
        String choose = null;
        while (!"exit".equals(choose = readString())) {
            if (Dish.allDishesToString().contains(choose)) {
                Dish dish = Dish.valueOf(choose);
                list.add(dish);
            } else
                writeMessage("Блюдо отсутствует в меню.");
        }
        return list;
    }
}
