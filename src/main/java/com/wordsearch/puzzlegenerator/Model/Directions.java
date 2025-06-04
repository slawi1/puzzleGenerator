package com.wordsearch.puzzlegenerator.Model;

public enum Directions {
    RIGHT(0, 1),
    LEFT(0, -1),
    DOWN(1, 0),
    UP(-1, 0),
    DIAGONAL_DOWN_RIGHT(1, 1),
    DIAGONAL_DOWN_LEFT(1, -1),
    DIAGONAL_UP_RIGHT(-1, 1),
    DIAGONAL_UP_LEFT(-1, -1);

    private final int rowDelta;
    private final int colDelta;

    Directions(int rowDelta, int colDelta) {
        this.rowDelta = rowDelta;
        this.colDelta = colDelta;
    }

    public int rowDelta() {
        return rowDelta;
    }

    public int colDelta() {
        return colDelta;
    }
}
