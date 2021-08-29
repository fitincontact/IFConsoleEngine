package com.ifce.engine.wordhandler.handlers;

import com.ifce.model.singletons.Game;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InitialWordHandler implements WordHandler {
    private final Game game;

    @Override
    public void exec() {

    }

    public WordHandler init(final String word) {
        game.getWord().add(word);
        return this;
    }
}