package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else denominations.put(denomination, count);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public int getTotalAmount() {
        return denominations.entrySet().stream()
                .collect(Collectors.summingInt(key -> key.getKey() * key.getValue()));
    }

    public boolean hasMoney() {
        if (getTotalAmount() > 0)
            return true;
        return false;
    }
}
