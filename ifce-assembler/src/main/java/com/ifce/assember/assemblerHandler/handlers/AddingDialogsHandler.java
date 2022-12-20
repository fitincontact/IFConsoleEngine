package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Add dialogs to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingDialogsHandler implements AssemblerHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getDialogAsmList().getDialogAsms().forEach(dialogAsm -> {
            val asmName = dialogAsm.getName();
            if (asmList.getObjects().isExistsDialog(asmName)) {
                error(String.format("Assembler.addDialogs: There is duplicate dialog name [%s]", asmName));
            } else if (asmList.getObjects().isExists(asmName)) {
                error(String.format("Assembler.addDialogs: There is duplicate object name [%s]", asmName));
            } else {
                //TODO
                asmList.getObjects().add(dialogAsm.getDialogs().get(0));
            }
        });
    }
}