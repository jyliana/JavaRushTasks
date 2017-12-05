package com.javarush.task.task24.task2413;

import java.awt.geom.Point2D;

import static java.lang.Math.max;

public abstract class BaseObject {
    private double x;
    private double y;
    private double radius;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public abstract void draw(Canvas canvas);

    public abstract void move();

    public boolean isIntersec(BaseObject o) {
        double distance = Point2D.distance(this.getX(), this.getY(), o.getX(), o.getY());
        return distance <= max(this.getRadius(), o.getRadius());
    }
}

