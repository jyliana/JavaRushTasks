package com.javarush.task.task40.task4004;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        Polygon pol = new Polygon();
        for (Point element : polygon) {
            pol.addPoint(element.x, element.y);
        }
        return pol.contains(point.x, point.y);
    }

}

