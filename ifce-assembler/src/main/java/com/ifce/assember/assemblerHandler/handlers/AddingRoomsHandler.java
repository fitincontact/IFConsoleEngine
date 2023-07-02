package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.singletons.Objects;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ifce.assember.assemblerHandler.handlers.AssemblerHandler.throwError;

/**
 * Add rooms to pool see {@link Objects}
 */
@RequiredArgsConstructor
@Component
public class AddingRoomsHandler implements CoRHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getRoomAsmList().getRoomAsms().forEach(roomAsm -> {
            var asmName = roomAsm.getName();
            if (asmList.getObjects().isExistsRoom(asmName)) {
                throwError(String.format("Assembler.addRooms: There is duplicate room name [%s]", asmName));
            } else if (asmList.getObjects().isExists(asmName)) {
                throwError(String.format("Assembler.addRooms: There is duplicate object name [%s]", asmName));
            } else {
                //todo check Objects.objectTypes in all handlers
                asmList.getObjects().add(roomAsm.getRoom());
            }
        });
    }
}
