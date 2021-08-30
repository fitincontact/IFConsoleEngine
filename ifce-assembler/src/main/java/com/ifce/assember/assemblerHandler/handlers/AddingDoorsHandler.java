package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.DoorAsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Add object to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingDoorsHandler implements AssemblerHandler {
    private final Objects objects;
    private final DoorAsmList doorAsmList;

    @Override
    public void exec() {
        doorAsmList.getDoorAsms().forEach(doorAsm -> {
            val asmName = doorAsm.getName();
            if (objects.isExistsDoor(asmName)) {
                error(String.format("Assembler.addDoors: There is duplicate door name [%s]", asmName));
            } else {
                objects.add(doorAsm.getDoor());
            }
        });
    }
}
