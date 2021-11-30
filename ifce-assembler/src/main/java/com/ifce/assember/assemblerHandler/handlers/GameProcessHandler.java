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

    @Override
    public void exec() {
        val player = asmList.getObjects().getItem(asmList.getGameAsm().getPlayerName());
        asmList.getGame().setPlayer(player);
        asmList.getGame().setAnnotation(asmList.getGameAsm().getAnnotation());
        asmList.getGame().setCurrentRoom(asmList.getObjects().getItemRoom(player));
    }
}