package com.wordsearch.puzzlegenerator.Controller;

import com.wordsearch.puzzlegenerator.Model.WordSearchRequest;
import com.wordsearch.puzzlegenerator.Model.WordSearchResult;
import com.wordsearch.puzzlegenerator.Service.PuzzleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String generatePuzzle(@ModelAttribute WordSearchRequest wordSearchRequest, Model model) {

        WordSearchResult result = puzzleService.generatePuzzle(wordSearchRequest);

        model.addAttribute("result", result);

        return "result";
    }
}
