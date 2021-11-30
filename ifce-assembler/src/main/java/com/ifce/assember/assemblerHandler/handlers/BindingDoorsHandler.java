package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Arrange doors in rooms
 */
@RequiredArgsConstructor
@Component
public class BindingDoorsHandler implements AssemblerHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getDoorAsmList().getDoorAsms().forEach(doorAsm -> {
            val asmDoorName = doorAsm.getName();
            val asmRoomFrom = doorAsm.getRoomFrom();
            val asmRoomTo = doorAsm.getRoomTo();
            val roomTo = asmList.getObjects().getRoom(asmRoomTo);
            if (roomTo == null) {
                error(String.format(
                        "Assembler.bindingDoors: For door name [%s] not found roomTo name [%s]",
                        asmDoorName,
                        asmRoomTo
                ));
            }

            val roomFrom = asmList.getObjects().getRoom(asmRoomFrom);
            if (roomFrom == null) {
                error(String.format(
                        "Assembler.bindingDoors: For door name [%s] not found roomFrom name [%s]",
                        asmDoorName,
                        asmRoomFrom
                ));
            } else {
                roomFrom.add(doorAsm.getDoor());
            }
        });
    }
}