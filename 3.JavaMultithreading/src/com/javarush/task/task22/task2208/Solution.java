package com.javarush.task.task22.task2208;

import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sbLine = new StringBuilder();
        for (Map.Entry<String, String> item : params.entrySet()) {
            if (item.getValue() != null) {
                sbLine.append(String.format("%s = '%s' and ", item.getKey(), item.getValue()));
            }
        }

        if (sbLine.length() > 0) {
            sbLine.replace(sbLine.lastIndexOf(" and"), sbLine.length(), "");
        }
        return sbLine.toString();

    }
}
