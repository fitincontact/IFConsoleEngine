package com.ifce.engine.wordhandler.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handle interaction for item to door
 */
@RequiredArgsConstructor
@Component
public class ItemToDoorInteractionWordHandler implements WordHandler {
    private final State state;

    @Override
    public void exec() {

    }
}