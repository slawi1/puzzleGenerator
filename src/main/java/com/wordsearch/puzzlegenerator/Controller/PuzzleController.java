package com.wordsearch.puzzlegenerator.Controller;

import com.wordsearch.puzzlegenerator.Model.Cell;
import com.wordsearch.puzzlegenerator.Model.WordSearchRequest;
import com.wordsearch.puzzlegenerator.Model.WordSearchResult;
import com.wordsearch.puzzlegenerator.Service.PuzzleService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class PuzzleController {

    public final PuzzleService puzzleService;

    public PuzzleController(PuzzleService puzzleService) {
        this.puzzleService = puzzleService;
    }


    @GetMapping("/")
    public ModelAndView showForm(ModelAndView modelAndView) {

        modelAndView.setViewName("index");
        modelAndView.addObject("wordSearchRequest", new WordSearchRequest());
        return modelAndView;
    }

    @PostMapping("/generate")
    public String  generatePuzzle(@ModelAttribute WordSearchRequest wordSearchRequest, Model model) {

        WordSearchResult result = puzzleService.generatePuzzle(wordSearchRequest);

        Cell[][] answer = new Cell[result.getPuzzle().length][result.getPuzzle()[0].length];
        Set<Pair<Integer, Integer>> pairs = result.getPairs();

        for (int row = 0; row < result.getPuzzle().length; row++) {
            for (int col = 0; col < result.getPuzzle()[row].length; col++) {
                char current = result.getPuzzle()[row][col];

                boolean placed = pairs.contains(Pair.of(row, col));

                answer[row][col] = new Cell(current, placed);
            }
        }

        model.addAttribute("answer", answer);
        model.addAttribute("result", result);

        return "result";
    }
}
