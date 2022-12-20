package com.ifce.engine.wordhandler.handlers;

import com.ifce.model.singletons.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Set  State {@link State} for new handler cycle
 */
@RequiredArgsConstructor
@Component
public class FinalizerWordHandler implements WordHandler {
    private final State state;

    @Override
    public void exec() {
        state.setOmit(false);
    }
}