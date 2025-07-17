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
public class WordPosition {

    private String word;
    private List<StringBuilder> coordinates;
}
