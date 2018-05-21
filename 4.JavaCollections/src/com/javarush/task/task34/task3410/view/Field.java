package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Home;
import com.javarush.task.task34.task3410.model.Player;

import javax.swing.*;
import java.awt.*;


public class Field extends JPanel {
    private View view;

    public Field(View view) {
        this.view = view;
    }

    public void paint(Graphics g) {
        Box box = new Box(14, 15);
        Player player = new Player(153, 158);
        Home home = new Home(200, 200);
        box.draw(g);
        player.draw(g);
        home.draw(g);
    }
}
