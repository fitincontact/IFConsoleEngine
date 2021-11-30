package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.singletons.Game;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Building game state see {@link Game}
 */
@RequiredArgsConstructor
@Component
public class GameProcessHandler implements AssemblerHandler {
    private final AsmList asmList;
    private final Game game;

    @Override
    public void exec() {
        val player = asmList.getObjects().getItem(asmList.getGameAsm().getPlayerName());
        game.setPlayer(player);
        game.setAnnotation(asmList.getGameAsm().getAnnotation());
        game.setCurrentRoom(asmList.getObjects().getItemRoom(player));
    }
}