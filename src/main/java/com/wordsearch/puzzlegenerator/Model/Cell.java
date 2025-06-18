package com.wordsearch.puzzlegenerator.Model;

import lombok.Getter;
@Getter
public class Cell {

    private char letter;
    private boolean isPartOfWord;

    public Cell(char letter, boolean isPartOfWord) {
        this.letter = letter;
        this.isPartOfWord = isPartOfWord;
    }

}
