package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.model.assembler.singletons.RoomAsmList;
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
        roomAsmList.getRooms().forEach(room -> {
            val asmName = room.getAsm().getName();
            if (objects.isExistsRoom(asmName)) {
                error(String.format("Assembler.addRooms: There is duplicate room name [%s]", asmName));
            } else {
                objects.add(room);
            }
        });
    }
}
