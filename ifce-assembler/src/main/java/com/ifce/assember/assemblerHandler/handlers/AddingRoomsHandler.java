package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.RoomAsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Add object to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingRoomsHandler implements AssemblerHandler {
    private final Objects objects;
    private final RoomAsmList roomAsmList;

    @Override
    public void exec() {
        roomAsmList.getRoomAsms().forEach(roomAsm -> {
            val asmName = roomAsm.getName();
            if (objects.isExistsRoom(asmName)) {
                error(String.format("Assembler.addRooms: There is duplicate room name [%s]", asmName));
            } else if (objects.isExists(asmName)) {
                error(String.format("Assembler.addRooms: There is duplicate object name [%s]", asmName));
            } else {
                //todo check Objects.objectTypes in all handlers
                objects.add(roomAsm.getRoom());
            }
        });
    }
}
