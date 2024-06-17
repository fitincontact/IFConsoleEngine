package com.ifce.engine.wordhandler.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handle interaction for item to item
 */
@RequiredArgsConstructor
@Component
public class ItemToItemInteractionWordHandler implements WordHandler {
    private final State state;

    @Override
    public void exec() {
    }

}