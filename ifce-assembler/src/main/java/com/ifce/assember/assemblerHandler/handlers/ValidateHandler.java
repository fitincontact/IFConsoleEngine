package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ifce.assember.assemblerHandler.handlers.AssemblerHandler.throwError;

/**
 * Validate game state
 */
@RequiredArgsConstructor
@Component
public class ValidateHandler implements CoRHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        validatePlayer();
    }

    private void validatePlayer() {
        var player = asmList.getItemAsmList().getItem(asmList.getGameAsm().getPlayerNames().get(0));
        if (player == null) {
            throwError("Assembler.ValidateHandler: Player is not created");
        }
    }
}