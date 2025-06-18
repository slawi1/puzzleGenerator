package com.wordsearch.puzzlegenerator.Model;

import org.apache.commons.lang3.tuple.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WordSearchResult {

    char[][] puzzle;

    List<String> addedWords;

    List<String> skippedWords;

    Set<Pair<Integer, Integer>> pairs;

}
