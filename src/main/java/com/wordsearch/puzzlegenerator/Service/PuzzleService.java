package com.wordsearch.puzzlegenerator.Service;


import org.apache.commons.lang3.tuple.Pair;
import com.wordsearch.puzzlegenerator.Model.*;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PuzzleService {

    public WordSearchResult generatePuzzle(WordSearchRequest request) {

        String[] splitWords = request.getInputWords().split("\\s+");
        List<String> words = Arrays.stream(splitWords)
                .map(String::trim)
                .filter(w -> !w.isEmpty())
                .map(String::toUpperCase)
                .toList();

        char[][] puzzle = new char[request.getRows()][request.getCols()];



        List<String> skippedWords = new ArrayList<>();
        List<String> addedWords = new ArrayList<>();
        Set<Pair<Integer, Integer>> pairs = new HashSet<>();

        for (String word : words) {
            boolean placed = false;

            for (int i = 0; i < 300; i++) {
                List<Directions> directions = Arrays.asList(Directions.values());
                Collections.shuffle(directions);
                Directions currentDirection = directions.getFirst();

                Random random = new Random();

                int row = request.getRows();
                int col = request.getCols();

                int minRow = currentDirection.rowDelta() == -1 ? word.length() - 1 : 0;
                int maxRow = currentDirection.rowDelta() == 1 ? row - word.length() : row - 1;

                int minCol = currentDirection.colDelta() == -1 ? word.length() - 1 : 0;
                int maxCol = currentDirection.colDelta() == 1 ? col - word.length() : col - 1;

                int startRow = random.nextInt(maxRow - minRow + 1) + minRow;
                int startCol = random.nextInt(maxCol - minCol + 1) + minCol;


                if (canPlaceWordAt(puzzle, word, startRow, startCol, currentDirection)) {
                    placeWord(puzzle, word, startRow, startCol, currentDirection);
                    addedWords.add(word);
                    for (int k = 0; k < word.length(); k++) {
                        int answerRow = startRow + k * currentDirection.rowDelta();
                        int answerCol = startCol + k * currentDirection.colDelta();

                        pairs.add(Pair.of(answerRow, answerCol));
                    }
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                skippedWords.add(word);
            }
        }
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] == '\0') {
                    puzzle[i][j] = '*';
                }
            }
        }
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Skipped:" + skippedWords);
        System.out.println("Placed:" + addedWords);



        return new WordSearchResult(puzzle, addedWords, skippedWords, pairs);

    }

    private void placeWord(char[][] puzzle, String word, int startRow, int startCol, Directions direction) {
        for (int i = 0; i < word.length(); i++) {
            int row = startRow + i * direction.rowDelta();
            int col = startCol + i * direction.colDelta();
            puzzle[row][col] = word.charAt(i);
        }
    }

    private boolean canPlaceWordAt(char[][] puzzle, String word, int startRow, int startCol, Directions direction) {

        for (int i = 0; i < word.length(); i++) {
            int row = startRow + i * direction.rowDelta();
            int col = startCol + i * direction.colDelta();
            if (row < 0 || row >= puzzle.length || col < 0 || col >= puzzle[0].length) {
                return false;
            }
            if (puzzle[row][col] != word.charAt(i) && puzzle[row][col] != '\0') {
                return false;
            }
        }
        return true;

    }
}


