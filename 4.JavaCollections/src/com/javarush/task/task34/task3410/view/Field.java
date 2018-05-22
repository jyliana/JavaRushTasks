package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Home;
import com.javarush.task.task34.task3410.model.Player;
import com.javarush.task.task34.task3410.model.Wall;

import javax.swing.*;
import java.awt.*;


public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
    }

    public void paint(Graphics g) {
        Box box = new Box(14, 15);
        Player player = new Player(153, 158);
        Home home = new Home(200, 200);
        Wall wall = new Wall(300, 300);
        box.draw(g);
        player.draw(g);
        home.draw(g);
        wall.draw(g);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
}
