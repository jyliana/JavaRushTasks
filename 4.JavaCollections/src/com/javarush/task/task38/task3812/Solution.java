package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest test = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Arrays.stream(test.fullyQualifiedNames()).forEach(System.out::println);
            return true;
        }
        return false;
    }

    public static boolean printValues(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest test = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Arrays.stream(test.value()).forEach(n -> System.out.println(n.getSimpleName()));
            return true;
        }
        return false;
    }
}
