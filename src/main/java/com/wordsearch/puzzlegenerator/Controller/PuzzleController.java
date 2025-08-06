package com.wordsearch.puzzlegenerator.Controller;

import com.wordsearch.puzzlegenerator.Model.WordSearchRequest;
import com.wordsearch.puzzlegenerator.Model.WordSearchResult;
import com.wordsearch.puzzlegenerator.Service.PuzzleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@SessionAttributes("result")
public class PuzzleController {


    private final PuzzleService puzzleService;

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

    @PostMapping("/download-pdf")
    public void downloadPdf(@RequestParam("html") String html, HttpServletResponse response) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3001/generate-pdf"))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString(html))
                .build();

        HttpResponse<InputStream> pdfResponse = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=page.pdf");

        InputStream in = pdfResponse.body();
        OutputStream out = response.getOutputStream();
        in.transferTo(out);
        out.flush();
    }

}
