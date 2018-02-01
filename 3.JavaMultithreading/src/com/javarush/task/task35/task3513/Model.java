package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final int FIELD_WIDTH = 4;
    protected int score;
    protected int maxTile;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

    public Model() {
        resetGameTiles();
    }

    private void addTile() {
        List<Tile> list = getEmptyTiles();
        if (list.size() > 0 && list != null) {
            Tile tile = list.get((int) (list.size() * Math.random()));
            tile.value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    list.add(gameTiles[i][j]);
                }
            }
        }
        return list;
    }

    public void resetGameTiles() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
        maxTile = 2;
        score = 0;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH - i - 1; j++) {
                if (tiles[j].value < tiles[j + 1].value && tiles[j].value == 0) {
                    int tmp = tiles[j].value;
                    tiles[j].value = tiles[j + 1].value;
                    tiles[j + 1].value = tmp;
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            if (tiles[i].value != 0 && tiles[i].value == tiles[i + 1].value) {
                tiles[i].value += tiles[i + 1].value;
                tiles[i + 1].value = 0;
                score += tiles[i].value;
                if (maxTile < tiles[i].value) {
                    maxTile = tiles[i].value;
                }
                i++;
                isChanged = true;
            }
        }
        if (isChanged) {
            compressTiles(tiles);
        }
        return isChanged;
    }

    public void left() {
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]))
                isChanged = true;
            if (mergeTiles(gameTiles[i]))
                isChanged = true;
        }
        if (isChanged) {
            addTile();
        }
    }

    public void right() {
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void up() {
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    public void down() {
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    private void rotate() {
        Tile[][] tmp_array = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tmp_array[j][i] = gameTiles[FIELD_WIDTH - i - 1][j];
            }
        }
        System.arraycopy(tmp_array, 0, gameTiles, 0, gameTiles.length);
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty())
            return true;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH - 1; j++) {
                if (gameTiles[j][i].value == gameTiles[j + 1][i].value || gameTiles[i][j].value == gameTiles[i][j + 1].value)
                    return true;
            }
        }
        return false;
    }
}
