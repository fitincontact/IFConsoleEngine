package com.ifce.engine.wordhandler.handlers;

import com.ifce.model.singletons.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemActHandler implements Handler{

    @Override
    public Game exec(Game game) {
        return game;
    }
}
