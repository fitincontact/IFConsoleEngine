package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Add doors to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingDoorsHandler implements AssemblerHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getDoorAsmList().getDoorAsms().forEach(doorAsm -> {
            val asmName = doorAsm.getName();
            if (asmList.getObjects().isExistsDoor(asmName)) {
                error(String.format("Assembler.addDoors: There is duplicate door name [%s]", asmName));
            } else if (asmList.getObjects().isExists(asmName)) {
                error(String.format("Assembler.addDoors: There is duplicate object name [%s]", asmName));
            } else {
                asmList.getObjects().add(doorAsm.getDoor());
            }
        });
    }
}