package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VeryComplexClass {

    public static void main(String[] args) {
    }

    public void veryComplexMethod() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D://logs");
    }
}
