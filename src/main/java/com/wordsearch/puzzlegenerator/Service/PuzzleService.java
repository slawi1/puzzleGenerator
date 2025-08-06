package com.wordsearch.puzzlegenerator.Service;

import com.wordsearch.puzzlegenerator.Model.*;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PuzzleService {

    public WordSearchResult generatePuzzle(WordSearchRequest request) {

        List<WordOverlay> wordOverlays = new ArrayList<>();

        String[] splitWords = request.getInputWords().split(",");
        List<String> words = Arrays.stream(splitWords)
                .map(String::strip)
                .filter(w -> !w.isEmpty())
                .map(String::toUpperCase)
                .toList();

        int longestWordLength = 0;
        for (String word : words) {
            if (word.contains(" ")) {
                word = word.replaceAll("\\s+", "");
            }
            if (word.length() > longestWordLength) {
                longestWordLength = word.length();
            }
        }

        int rowSize = request.getRows();
        int colSize = request.getCols();

        if (longestWordLength <= rowSize && longestWordLength <= colSize) {


        char[][] puzzle = new char[rowSize][colSize];

        List<String> skippedWords = new ArrayList<>();
        List<String> addedWords = new ArrayList<>();

        for (String word : words) {

            if (word.contains(" ")) {
                word = word.replaceAll("\\s+", "");
            }
            boolean placed = false;

            for (int i = 0; i < 500; i++) {
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
                    WordOverlay overlay = WordOverlay.builder()
                            .word(word)
                            .startX(startCol)
                            .startY(startRow)
                            .endX(Math.abs(startCol + (word.length() - 1) * currentDirection.colDelta()))
                            .endY(Math.abs(startRow + (word.length() - 1) * currentDirection.rowDelta()))
                            .build();
                    wordOverlays.add(overlay);
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
                    Random random = new Random();
                    int letter = random.nextInt(90 - 65) + 65;
                    char randomLetter = (char) letter;
                    puzzle[i][j] = randomLetter;
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

        return new WordSearchResult(request.getPuzzleName(), puzzle, rowSize, colSize, addedWords, skippedWords, wordOverlays);

        }else {
            throw new DomainException("Word length cannot be greater than row length or col length");
        }
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


