package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency obj) {
        if (numberOfEmptyTiles != obj.numberOfEmptyTiles) {
            return Integer.compare(numberOfEmptyTiles, obj.numberOfEmptyTiles);
        } else if (score != obj.score) {
            return Integer.compare(score, obj.score);
        }
        return 0;
    }
}
