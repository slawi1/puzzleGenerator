package com.wordsearch.puzzlegenerator.Model;

public class WordPlacement {

    private final String word;
    private final int startRow;
    private final int startCol;
    private final Directions direction;

    public WordPlacement(String word, int startRow, int startCol, Directions direction) {
        this.word = word;
        this.startRow = startRow;
        this.startCol = startCol;
        this.direction = direction;
    }

    public String getWord() {
        return word;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public Directions getDirection() {
        return direction;
    }
}
