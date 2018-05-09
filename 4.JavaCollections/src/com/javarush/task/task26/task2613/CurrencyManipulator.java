package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;
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

    public boolean isAmountAvailable(int expectedAmount) {
        if (getTotalAmount() >= expectedAmount)
            return true;
        else return false;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> copy = denominations.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Map<Integer, Integer> copyRepeat = new HashMap<>();
        copyRepeat.putAll(copy);
        Map<Integer, Integer> result = new HashMap<>();
        int tmpAmount = expectedAmount;

        while (tmpAmount != 0 && copy.size() != 0) {
            for (Integer element : copy.keySet()) {
                if (element <= tmpAmount) {
                    while (element <= tmpAmount && copy.get(element) != 0) {
                        if (result.containsKey(element)) {
                            result.put(element, result.get(element) + 1);
                        } else {
                            result.put(element, 1);
                        }
                        copy.put(element, copy.get(element) - 1);
                        tmpAmount -= element;
                    }
                    if (tmpAmount == 0)
                        break;
                }
            }
            if (tmpAmount != 0) {
                Optional<Map.Entry<Integer, Integer>> in = copy.entrySet().stream()
                        .findFirst();
                copyRepeat.remove(in.get().getKey());
                copy.clear();
                copy.putAll(copyRepeat);
                tmpAmount = expectedAmount;
                result = new HashMap<>();
            }
        }
        if (tmpAmount != 0) {
            throw new NotEnoughMoneyException();
        }
        for (Map.Entry item : copy.entrySet()) {
            if ((int) item.getValue() == 0) {
                denominations.remove(item.getKey());
            } else if ((int) item.getValue() != denominations.get(item.getKey()))
                denominations.put((int) item.getKey(), (int) item.getValue());
        }
        return result;
    }
}
