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
        Tile tile = list.get((int) (list.size() * Math.random()));
        tile.value = Math.random() < 0.9 ? 2 : 4;
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

    private void compressTiles(Tile[] tiles) {

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH - i - 1; j++) {
                if (tiles[j].value < tiles[j + 1].value && tiles[j].value == 0) {
                    int tmp = tiles[j].value;
                    tiles[j].value = tiles[j + 1].value;
                    tiles[j + 1].value = tmp;
                }
            }
        }
    }

    private void mergeTiles(Tile[] tiles) {
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            if (tiles[i].value != 0 && tiles[i].value == tiles[i + 1].value) {
                tiles[i].value += tiles[i + 1].value;
                tiles[i + 1].value = 0;
                score += tiles[i].value;
                if (maxTile < tiles[i].value) {
                    maxTile = tiles[i].value;
                }
                i++;
            }
        }
        compressTiles(tiles);
    }
}
