package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.singletons.Objects;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ifce.assember.assemblerHandler.handlers.AssemblerHandler.throwError;

/**
 * Add doors to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingDoorsHandler implements CoRHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getDoorAsmList().getDoorAsms().forEach(doorAsm -> {
            var asmName = doorAsm.getName();
            if (asmList.getObjects().isExistsDoor(asmName)) {
                throwError(String.format("Assembler.addDoors: There is duplicate door name [%s]", asmName));
            } else if (asmList.getObjects().isExists(asmName)) {
                throwError(String.format("Assembler.addDoors: There is duplicate object name [%s]", asmName));
            } else {
                asmList.getObjects().add(doorAsm.getDoor());
            }
        });
    }
}
