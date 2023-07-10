package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

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
        asmList.getPlayerNames().forEach(asmPlayer -> {
            var player = asmList.getItemAsmList().getItem(asmPlayer);
            if (player == null) {
                throwError(MessageFormat.format("Assembler.ValidateHandler: Player {0} is not created", asmPlayer));
            }
        });
    }
}