package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.singletons.Objects;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ifce.assember.assemblerHandler.handlers.AssemblerHandler.throwError;

/**
 * Add dialogs to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingDialogsHandler implements CoRHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getDialogAsmList().getDialogAsmList().forEach(dialogAsm -> {
            var asmName = dialogAsm.getName();
            if (asmList.getObjects().isExistsDialog(asmName)) {
                throwError(String.format("Assembler.addDialogs: There is duplicate dialog name [%s]", asmName));
            } else if (asmList.getObjects().isExists(asmName)) {
                throwError(String.format("Assembler.addDialogs: There is duplicate object name [%s]", asmName));
            } else {
                //TODO
                asmList.getObjects().add(dialogAsm.getDialogs().get(0));
            }
        });
    }
}