package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.asList(sort(new Integer[]{13, 8, 15, 5, 17})));

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

        Comparator<Integer> sortByMedian = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(median - o1) - Math.abs(median - o2);
            }
        };
        /*Arrays.sort(array, (x, y) -> Integer.compare(Math.abs(median - x), Math.abs(median - y)));*/
        Arrays.sort(array, sortByMedian);
        return array;
    }
}
