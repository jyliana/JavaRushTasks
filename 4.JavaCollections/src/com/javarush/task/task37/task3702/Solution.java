package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class Solution {
    public static void main(String[] args) {
        MaleFactory factory = new MaleFactory();
        System.out.println(factory.getPerson(99));
        System.out.println(factory.getPerson(4));
        System.out.println(factory.getPerson(15));

        FemaleFactory factory2 = new FemaleFactory();
        System.out.println(factory2.getPerson(99));
        System.out.println(factory2.getPerson(4));
        System.out.println(factory2.getPerson(15));
    }
}
