package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Arrange dialogs in items
 */
@RequiredArgsConstructor
@Component
public class BindingDialogsHandler implements CoRHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
//        dialogAsmList.getDialogAsms().forEach(dialogAsm -> {
//            var dialogAsmName = dialogAsm.getName();
//            var asmRoomFrom = dialogAsm.getItem();
//            var asmRoomTo = dialogAsm.getRoomTo();
//            var roomTo = objects.getRoom(asmRoomTo);
//            if (roomTo == null) {
//                error(String.format(
//                        "Assembler.bindingDoors: For door name [%s] not found roomTo name [%s]",
//                        dialogAsmName,
//                        asmRoomTo
//                ));
//            }
//
//            var roomFrom = objects.getRoom(asmRoomFrom);
//            if (roomFrom == null) {
//                error(String.format(
//                        "Assembler.bindingDoors: For door name [%s] not found roomFrom name [%s]",
//                        dialogAsmName,
//                        asmRoomFrom
//                ));
//            } else {
//                roomFrom.add(dialogAsm.getDoor());
//            }
//        });
    }
}