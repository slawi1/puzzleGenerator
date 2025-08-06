package com.wordsearch.puzzlegenerator.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordSearchRequest {

    String puzzleName;

    String inputWords;

    int rows;

    int cols;

}
