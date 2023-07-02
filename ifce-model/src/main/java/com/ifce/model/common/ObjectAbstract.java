package com.ifce.model.common;

import lombok.Data;

/**
 * Parent class for game objects
 */
@Data
public abstract class ObjectAbstract {
    /**
     * Strings for matching with players console word
     */
    private Word name = new Word();

    public void add(final String name) {
        this.name.add(name);
    }

    public String getName() {
        return name.getWord();
    }
}