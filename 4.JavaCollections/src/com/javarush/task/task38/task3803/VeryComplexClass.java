package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public static void main(String[] args) {
    }

    public void methodThrowsClassCastException() throws ClassCastException {
        Object i = "test";
        Integer s = (Integer) i;
    }

    public void methodThrowsNullPointerException() throws NullPointerException {
        String line = null;
        if (line.equals("test")) {
            System.out.println("Something wrong! This test should not pass");
        }
    }
}
