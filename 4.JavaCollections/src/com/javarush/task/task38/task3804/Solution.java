package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        System.out.println(FactoryClass.returnException(ExceptionDBMessage.RESULT_HAS_NOT_GOTTEN_BECAUSE_OF_TIMEOUT).getMessage());
        return FactoryClass.class;
    }

    public static void main(String[] args) {
        System.out.println(Solution.getFactoryClass());
    }
}