package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isOneEditAway(String first, String second) {
        String longest;
        String shortest;

        if (first == null || second == null)
            return false;
        int length = first.length() - second.length();
        if (Math.abs(length) > 1)
            return false;
        if (first.equals(second)) return true;

        int mistakesAllowed = 1;
        if (first.length() == second.length()) {
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    mistakesAllowed--;
                    if (mistakesAllowed < 0) {
                        return false;
                    }
                }
            }
        } else {
            if (length == 1) {
                longest = first;
                shortest = second;
            } else {
                longest = second;
                shortest = first;
            }
            if (longest.contains(shortest))
                return true;
            for (int i = 0; i < longest.length(); i++) {
                if (shortest.charAt(i) != longest.charAt(i)) {
                    mistakesAllowed--;
                    longest = longest.substring(0, i) + longest.substring(i + 1);
                    i--;
                    if (mistakesAllowed < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
