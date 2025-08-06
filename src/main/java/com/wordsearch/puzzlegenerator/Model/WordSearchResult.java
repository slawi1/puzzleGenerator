package com.wordsearch.puzzlegenerator.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WordSearchResult {

    String puzzleName;

    char[][] puzzle;

    int rowSize;

    int colSize;

    List<String> addedWords;

    List<String> skippedWords;

    List<WordOverlay> wordOverlays;

}
