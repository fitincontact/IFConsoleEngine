package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.singletons.Game;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Building game state see {@link Game}
 */
@RequiredArgsConstructor
@Component
public class GameProcessHandler implements CoRHandler {
    private final AsmList asmList;
    private final Game game;

    @Override
    public void exec() {
        var playerNames = asmList.getGameAsm().getPlayerNames();
        var players = new java.util.ArrayList<>(playerNames.stream().map(name -> asmList.getItemAsmList().getItem(name)).toList());
        var player = players.get(0);
        game.setPlayer(player);

        players.remove(player);
        game.setPlayers(players);
        game.setAnnotation(asmList.getGameAsm().getAnnotation());
        game.setCurrentRoom(asmList.getObjects().getItemRoom(player));
    }
}