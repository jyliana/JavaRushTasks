package com.javarush.task.task26.task2601;

import java.util.Arrays;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        final int median;
        int length = array.length / 2;
        if (array.length % 2 == 0) {
            median = (array[length - 1] + array[length]) / 2;
        } else {
            median = array[length];
        }

        Arrays.sort(array, (x, y) -> Integer.compare(Math.abs(median - x), Math.abs(median - y)));
        return array;
    }
}
