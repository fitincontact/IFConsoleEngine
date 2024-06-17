package com.ifce.engine.wordhandler.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Init Game {@link Game}
 */
@RequiredArgsConstructor
@Component
public class InitialWordHandler implements WordHandler {
    private final State state;

    @Override
    public void exec() {
        defineWordType();
    }

    private void defineWordType() {

    }

    public WordHandler init(final String word) {
        state.getConsoleWords().add(word);
        return this;
    }
}