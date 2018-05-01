package com.javarush.task.task39.task3904;

/*
Лестница
*/
public class Solution {
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + countPossibleRunups(n));
    }

    public static long countPossibleRunups(int n) {
        if (n == 0)
            return 1;
        else if (n < 0)
            return 0;

        long[] res = new long[n + 1];
        res[0] = 1;
        res[1] = 1;
        res[2] = 2;

        for (int i = 3; i <= n; i++)
            res[i] = res[i - 1] + res[i - 2]
                    + res[i - 3];

        return res[n];
    }
}

