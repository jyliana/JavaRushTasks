package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;

/* 
CountDownLatch
*/
public class Solution {
    CountDownLatch latch = new CountDownLatch(1);
    private volatile boolean isWaitingForValue = true;

    public static void main(String[] args) throws InterruptedException {
        Solution solution = new Solution();
        solution.someMethod();

    }

    public void someMethod() throws InterruptedException {
        latch.await();
        retrieveValue();
        latch.countDown();
    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }
}
