package com.ifce.engine.wordhandler;

import com.ifce.engine.wordhandler.handlers.Handler;
import com.ifce.model.etc.Game;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
public class WordHandlerService {
    private final Game state;
    private final Handler currentHandler;

    public WordHandlerService(Game state,Handler currentHandler) {
        this.state = state;
        this.currentHandler = currentHandler;
    }

    public WordHandlerService addHandler(final Handler handler) {
        return new WordHandlerService(
                state,
                (st) -> {
                  return    handler.exec(st);
                }
        );
    }

    public void exec() {
        currentHandler.exec(state);
    }
}
