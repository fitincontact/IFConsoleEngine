package com.ifce.engine.wordhandler;

import com.ifce.engine.wordhandler.handlers.Handler;
import com.ifce.model.singletons.Game;

//@RequiredArgsConstructor
public class WordHandler {
    private final Game state;
    private final Handler currentHandler;

    public WordHandler(Game state, Handler currentHandler) {
        this.state = state;
        this.currentHandler = currentHandler;
    }

    public WordHandler addHandler(final Handler handler) {
        return new WordHandler(
                state,
                (st) -> {
                    return handler.exec(st);
                }
        );
    }

    public void exec() {
        currentHandler.exec(state);
    }
}
