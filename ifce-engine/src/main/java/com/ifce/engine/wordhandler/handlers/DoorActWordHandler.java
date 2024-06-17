package com.ifce.engine.wordhandler.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handle opening door
 */
@RequiredArgsConstructor
@Component
public class DoorActWordHandler implements WordHandler {
    private final State state;

    @Override
    public void exec() {

    }
}