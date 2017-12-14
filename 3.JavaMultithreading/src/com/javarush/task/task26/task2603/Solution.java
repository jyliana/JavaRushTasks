package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static void main(String[] args) {
       /* List<Man> man = new ArrayList<>();
        man.add(new Man("Бешкек", "Абдурасул", 13));
        man.add(new Man("Анатолий", "Вассерман", 77));
        man.add(new Man("Анатолий", "Булкин", 90));
        man.add(new Man("Василий", "Теркин", 7));
        man.add(new Man("Анатолий", "Вассерман", 64));

        Comparator<Man> sortByName = new Comparator<Man>() {
            @Override
            public int compare(Man o1, Man o2) {
                return o1.name.compareTo(o2.name);
            }
        };

        Comparator<Man> sortBySurname = new Comparator<Man>() {
            @Override
            public int compare(Man o1, Man o2) {
                return o1.surname.compareTo(o2.surname);
            }
        };
        Comparator<Man> sortByAge = new Comparator<Man>() {
            @Override
            public int compare(Man o1, Man o2) {
                return o1.age - o2.age;
            }
        };

        Collections.sort(man, new CustomizedComparator<Man>(sortByName, sortBySurname, sortByAge));

        for (Man item : man) {
            System.out.println(item.name + " " + item.surname + " " + item.age);
        }*/
    }

    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(Object o1, Object o2) {
            int result = 0;
            for (Comparator comparator : comparators) {
                result = comparator.compare(o1, o2);
                if (result != 0)
                    break;
            }
            return result;
        }
    }

   /* static class Man {
        String name;
        String surname;
        Integer age;

        public Man(String name, String surname, Integer age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
        }
    }*/
}
