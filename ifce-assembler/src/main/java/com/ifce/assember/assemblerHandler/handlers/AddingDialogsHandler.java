package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.model.assembler.singletons.DialogAsmList;
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
        dialogAsmList.getDialogs().forEach(dialog -> {
            val asmName = dialog.getAsm().getName();
            if (objects.isExistsDialog(asmName)) {
                error(String.format("Assembler.addDialogs: There is duplicate dialog name [%s]", asmName));
            } else {
                objects.add(dialog);
            }
        });
    }
}