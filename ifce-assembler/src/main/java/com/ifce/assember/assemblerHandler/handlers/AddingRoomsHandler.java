package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Add rooms to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingRoomsHandler implements AssemblerHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getRoomAsmList().getRoomAsms().forEach(roomAsm -> {
            val asmName = roomAsm.getName();
            if (asmList.getObjects().isExistsRoom(asmName)) {
                error(String.format("Assembler.addRooms: There is duplicate room name [%s]", asmName));
            } else if (asmList.getObjects().isExists(asmName)) {
                error(String.format("Assembler.addRooms: There is duplicate object name [%s]", asmName));
            } else {
                //todo check Objects.objectTypes in all handlers
                asmList.getObjects().add(roomAsm.getRoom());
            }
        });
    }
}
