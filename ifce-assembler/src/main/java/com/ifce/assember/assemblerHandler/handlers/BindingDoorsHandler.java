package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.model.assembler.singletons.DoorAsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Arrange doors in rooms
 */
@RequiredArgsConstructor
@Component
public class BindingDoorsHandler implements AssemblerHandler {
    private final Objects objects;
    private final DoorAsmList doorAsmList;

    @Override
    public void exec() {
        doorAsmList.getDoors().forEach(door -> {
            val asmDoorName = door.getAsm().getName();
            val asmRoomFrom = door.getAsm().getRoomFrom();
            val asmRoomTo = door.getAsm().getRoomTo();

            val roomTo = objects.getRoom(asmRoomTo);
            if (roomTo == null) {
                error(String.format(
                        "Assembler.bindingDoors: For door name [%s] not found roomTo name [%s]",
                        asmDoorName,
                        asmRoomTo
                ));
            }

            val roomFrom = objects.getRoom(asmRoomFrom);
            if (roomFrom == null) {
                error(String.format(
                        "Assembler.bindingDoors: For door name [%s] not found roomFrom name [%s]",
                        asmDoorName,
                        asmRoomFrom
                ));
            } else {
                roomFrom.add(door);
            }
        });
    }
}