package com.service.model.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Processing word
 * <p>
 * word is name of items, rooms, doors.
 */
public class Words {
    private final List<String> words = new ArrayList<>();

    public void add(final String word) {
        words.add(word);
    }

    public void add(final String... words) {
        for (var word : words) {
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

    public boolean isContent(String name) {
        return words.contains(name);
    }
}
