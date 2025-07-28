package com.wordsearch.puzzlegenerator.Model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WordOverlay {

    private String word;

    private int startX;

    private int startY;

    private int endX;

    private int endY;
}
