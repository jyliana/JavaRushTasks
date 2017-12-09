package com.javarush.task.task25.task2512;

import java.util.LinkedList;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

   /* public Solution() {
        try {
            throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
        } catch (Exception e) {
            uncaughtException(Thread.currentThread(), e);
        }
    }*/


    public static void main(String[] args) {
        /*Solution solution = new Solution();*/
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        Throwable th = e;
        List<Throwable> list = new LinkedList<>();
        while (th != null) {
            list.add(0, th);
            th = th.getCause();
        }
        for (Throwable item : list) {
            System.out.println(item);
        }
    }
}
