package com.javarush.task.task29.task2909.human;

public class Worker {
    public String company;
    private Human human;
    private double salary;

    public Worker(String name, int age) {
        human = new Human(name, age);
    }

    public void live() {

    }

    public double getSalary() {
        return salary;
    }

    public void setSlr(double salary) {
        this.salary = salary;
    }
}