package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.GameAsm;
import com.ifce.model.singletons.Game;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Building game state see {@link Game}
 */
@RequiredArgsConstructor
@Component
public class GameProcessHandler implements AssemblerHandler {
    private final Objects objects;
    private final GameAsm gameAsm;
    private final Game game;

    @Override
    public void exec() {
        val player = objects.getItem(gameAsm.getPlayerName());
        if (player == null) {
            error("Assembler.gameProcess: Player is not created");
        } else {
            game.setPlayer(player);
            game.setAnnotation(gameAsm.getAnnotation());
            game.setCurrentRoom(game.getItemRoom(player));
        }
    }
}