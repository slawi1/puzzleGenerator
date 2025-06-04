package com.wordsearch.puzzlegenerator.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordSearchRequest {

    String inputWords;

    int rows;

    int cols;

}
