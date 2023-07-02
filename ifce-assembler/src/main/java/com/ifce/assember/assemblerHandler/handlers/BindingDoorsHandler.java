package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ifce.assember.assemblerHandler.handlers.AssemblerHandler.throwError;

/**
 * Arrange doors in rooms
 */
@RequiredArgsConstructor
@Component
public class BindingDoorsHandler implements CoRHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getDoorAsmList().getDoorAsms().forEach(doorAsm -> {
            var asmDoorName = doorAsm.getName();
            var asmRoomFrom = doorAsm.getRoomFrom();
            var asmRoomTo = doorAsm.getRoomTo();
            var roomTo = asmList.getObjects().getRoom(asmRoomTo);
            if (roomTo == null) {
                throwError(String.format(
                        "Assembler.bindingDoors: For door name [%s] not found roomTo name [%s]",
                        asmDoorName,
                        asmRoomTo
                ));
            }

            var roomFrom = asmList.getObjects().getRoom(asmRoomFrom);
            if (roomFrom == null) {
                throwError(String.format(
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