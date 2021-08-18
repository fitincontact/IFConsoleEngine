package com.ifce.model.etc;

import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private final List<String> words = new ArrayList<>();

    public void add(final String word) {
        words.add(word);
    }

    public void add(final String... words) {
        for (val word : words) {
            add(word);
        }
    }

    /**
     * Return first word
     *
     * @return word
     */
    public String getWord() {
        if (words.get(0) != null) {
            return words.get(0);
        } else {
            return "";
        }
    }
}