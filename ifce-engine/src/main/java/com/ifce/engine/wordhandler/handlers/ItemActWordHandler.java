package com.ifce.engine.wordhandler.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handle word for item
 */
@RequiredArgsConstructor
@Component
public class ItemActWordHandler implements WordHandler {
    private final State state;

    @Override
    public void exec() {
    }

}
