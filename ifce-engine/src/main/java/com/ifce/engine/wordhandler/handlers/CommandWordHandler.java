package com.ifce.engine.wordhandler.handlers;

import com.ifce.model.singletons.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handle command like - show inventory, finish game, save, load and etc {@link com.ifce.format.Format}
 */
@RequiredArgsConstructor
@Component
public class CommandWordHandler implements WordHandler {
    private final State state;

    @Override
    public void exec() {

    }
}