package com.ifce.engine.wordhandler.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handle not defined word
 */
@RequiredArgsConstructor
@Component
public class UnknownWordHandler implements WordHandler {
    private final State state;

    @Override
    public void exec() {

    }
}