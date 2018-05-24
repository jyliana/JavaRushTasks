package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

import static java.awt.event.KeyEvent.*;


public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void paint(Graphics g) {
       /* Box box = new Box(14, 15);
        Player player = new Player(153, 158);
        Home home = new Home(200, 200);
        Wall wall = new Wall(300, 300);
        box.draw(g);
        player.draw(g);
        home.draw(g);
        wall.draw(g);*/
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 300, 300);

        Set<GameObject> objects = view.getGameObjects().getAll();
        objects.stream().forEach(object -> object.draw(g));


    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case VK_R:
                    eventListener.restart();
                    break;
            }
        }
    }

}
