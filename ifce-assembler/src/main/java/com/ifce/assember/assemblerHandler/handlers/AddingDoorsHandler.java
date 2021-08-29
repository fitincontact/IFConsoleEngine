package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.model.assembler.singletons.DoorAsmList;
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
        doorAsmList.getDoors().forEach(door -> {
            val asmName = door.getAsm().getName();
            if (objects.isExistsDoor(asmName)) {
                error(String.format("Assembler.addDoors: There is duplicate door name [%s]", asmName));
            } else {
                objects.add(door);
            }
        });
    }
}
