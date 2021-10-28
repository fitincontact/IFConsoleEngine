package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Validate game state
 */
@RequiredArgsConstructor
@Component
public class ValidateHandler implements AssemblerHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        validatePlayer();
    }

    private void validatePlayer() {
        val player = asmList.getItemAsmList().getItem(asmList.getGameAsm().getPlayerName());
        if (player == null) {
            error("Assembler.ValidateHandler: Player is not created");
        }
    }
}