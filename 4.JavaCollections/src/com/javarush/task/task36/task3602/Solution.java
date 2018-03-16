package com.javarush.task.task36.task3602;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        for (Class clazz : Collections.class.getDeclaredClasses()) {
            if (List.class.isAssignableFrom(clazz) && Modifier.isStatic(clazz.getModifiers()) && Modifier.isPrivate(clazz.getModifiers())) {
                try {
                    if (clazz.getDeclaredConstructor().getParameterCount() == 0) {
                        return clazz;
                    }
                } catch (Exception e) {

                }
            }
        }
        return null;
    }
}
