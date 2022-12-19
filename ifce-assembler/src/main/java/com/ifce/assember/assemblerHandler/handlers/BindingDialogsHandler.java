package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.singletons.AsmList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Arrange dialogs in items
 */
@RequiredArgsConstructor
@Component
public class BindingDialogsHandler implements AssemblerHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
//        dialogAsmList.getDialogAsms().forEach(dialogAsm -> {
//            val dialogAsmName = dialogAsm.getName();
//            val asmRoomFrom = dialogAsm.getItem();
//            val asmRoomTo = dialogAsm.getRoomTo();
//            val roomTo = objects.getRoom(asmRoomTo);
//            if (roomTo == null) {
//                error(String.format(
//                        "Assembler.bindingDoors: For door name [%s] not found roomTo name [%s]",
//                        dialogAsmName,
//                        asmRoomTo
//                ));
//            }
//
//            val roomFrom = objects.getRoom(asmRoomFrom);
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