package com.ifce.engine.wordhandler;

import com.ifce.engine.wordhandler.handlers.WordHandler;

/**
 * Executor for word handlers
 */
public class WordExecutor {
    private WordHandler currentWordHandler;

    public WordExecutor(final WordHandler wordHandler) {
        currentWordHandler = wordHandler;
    }

    public WordExecutor() {
    }

    public WordExecutor addHandler(final WordHandler wordHandler) {
        if (currentWordHandler != null) {
            currentWordHandler.exec();
        }
        return new WordExecutor(
                wordHandler
        );
    }

    public void exec() {
        currentWordHandler.exec();
    }
}
