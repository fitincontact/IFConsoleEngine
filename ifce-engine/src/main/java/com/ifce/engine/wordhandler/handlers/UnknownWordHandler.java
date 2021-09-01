package com.ifce.engine.wordhandler.handlers;

import com.ifce.model.singletons.Game;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UnknownWordHandler  implements WordHandler {
    private final Game game;

    @Override
    public void exec() {

    }
}