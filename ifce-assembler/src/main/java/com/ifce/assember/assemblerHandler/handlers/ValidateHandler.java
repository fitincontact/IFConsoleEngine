package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.GameAsm;
import com.ifce.assember.model.singletons.ItemAsmList;
import com.ifce.model.main.Item;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Validate game state
 */
@RequiredArgsConstructor
@Component
public class ValidateHandler implements AssemblerHandler {
    private final ItemAsmList itemAsmList;
    private final GameAsm gameAsm;

    @Override
    public void exec() {
        getPlayer();
    }

    private Item getPlayer() {
        val player = itemAsmList.getItem(gameAsm.getPlayerName());
        if (player == null) {
            error("Assembler.BindingItems: Player is not created");
        }
        return player;
    }
}