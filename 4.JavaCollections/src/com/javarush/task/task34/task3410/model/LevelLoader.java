package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        int x = FIELD_CELL_SIZE / 2;
        int y = FIELD_CELL_SIZE / 2;

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = new Player(x, y);
        walls.add(new Wall(x, y));
        walls.add(new Wall(x, y));
        walls.add(new Wall(x, y));
        walls.add(new Wall(x, y));
        boxes.add(new Box(x, y));
        homes.add(new Home(x, y));

        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
        return gameObjects;
    }
}
