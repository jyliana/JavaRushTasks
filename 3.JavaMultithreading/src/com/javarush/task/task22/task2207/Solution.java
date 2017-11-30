package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader = new BufferedReader(new FileReader(reader.readLine()));

        StringBuilder stringBuilder = new StringBuilder();
        while (reader.ready()) {
            stringBuilder.append(" " + reader.readLine());
        }
        reader.close();

        List<String> list = new LinkedList<>(Arrays.asList(stringBuilder.toString().trim().split(" ")));
        int count = list.size();
        for (int i = 0; i < count; i++) {
            String tmp = new StringBuilder(list.get(i)).reverse().toString();
            for (int j = i + 1; j < count; j++) {
                if (tmp.equals(list.get(j))) {
                    Pair pair = new Pair();
                    pair.first = list.get(j).toString();
                    pair.second = list.get(i).toString();
                    result.add(pair);
                    list.remove(i);
                    list.remove(j - 1);
                    count -= 2;
                    i--;
                    break;
                }
            }
        }
        for (Pair p : result) {
            System.out.println(p);
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
