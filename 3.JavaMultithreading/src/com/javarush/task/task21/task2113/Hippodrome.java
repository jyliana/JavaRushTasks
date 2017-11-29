package com.javarush.task.task21.task2113;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Hippodrome {

    public static Hippodrome game;
    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses;

        Horse horse1 = new Horse("Selena", 3, 0);
        Horse horse2 = new Horse("Zeus", 3, 0);
        Horse horse3 = new Horse("Artemis", 3, 0);
        horses = Arrays.asList(horse1, horse2, horse3);
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        horses.sort(Comparator.comparingDouble(Horse::getDistance).reversed());
        return horses.get(0);
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}

