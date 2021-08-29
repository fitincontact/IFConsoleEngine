package com.ifce.engine.wordhandler;

public class WordHandler {
    private com.ifce.engine.wordhandler.handlers.WordHandler currentWordHandler;

    public WordHandler(final com.ifce.engine.wordhandler.handlers.WordHandler wordHandler) {
        currentWordHandler = wordHandler;
    }

    public WordHandler() {
    }

    public WordHandler addHandler(final com.ifce.engine.wordhandler.handlers.WordHandler wordHandler) {
        currentWordHandler.exec();
        return new WordHandler(
                wordHandler
        );
    }

    public void exec() {
        currentWordHandler.exec();
    }
}
