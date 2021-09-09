package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.DialogAsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Add object to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingDialogsHandler implements AssemblerHandler {
    private final Objects objects;
    private final DialogAsmList dialogAsmList;

    @Override
    public void exec() {
        dialogAsmList.getDialogAsms().forEach(dialogAsm -> {
            val asmName = dialogAsm.getName();
            if (objects.isExistsDialog(asmName)) {
                error(String.format("Assembler.addDialogs: There is duplicate dialog name [%s]", asmName));
            } else if (objects.isExists(asmName)) {
                error(String.format("Assembler.addDialogs: There is duplicate object name [%s]", asmName));
            } else {
                //TODO
                objects.add(dialogAsm.getDialogs().get(0));
            }
        });
    }
}