package com.ifce.engine.wordhandler.handlers;

import com.ifce.model.singletons.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemActWordHandler implements WordHandler {
    private final Game game;

    @Override
    public void exec() {
    }

}
