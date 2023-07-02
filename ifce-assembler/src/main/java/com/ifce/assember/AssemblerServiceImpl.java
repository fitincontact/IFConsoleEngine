package com.ifce.assember;

import com.ifce.assember.assemblerHandler.AssemblerHandlerService;
import com.ifce.assember.model.*;
import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.common.*;
import com.ifce.service.AssemblerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssemblerServiceImpl implements AssemblerService {
    @Autowired
    private AssemblerHandlerService assemblerHandlerService;

    @Autowired
    private AsmList asmList;

    public AssemblerServiceImpl(AssemblerHandlerService assemblerHandlerService, AsmList asmList) {
    }

    @Override
    public void start(String annotation) {
        asmList.getGameAsm().setAnnotation(annotation);
        assemblerHandlerService.handle();
    }

    @Override
    public Player getPlayer(String name, String place) {
        var asm = new PlayerAsm(name, place, asmList.getObjects());
        asmList.getPlayerAsmList().add(asm);
        return asm.getPlayer();
    }

    @Override
    public Room getRoom(String name) {
        var asm = new RoomAsm(name);
        asmList.getRoomAsmList().add(asm);
        return asm.getRoom();
    }

    @Override
    public Door getDoor(String name, String roomFrom, String roomTo) {
        var asm = new DoorAsm(name, roomFrom, roomTo);
        asmList.getDoorAsmList().add(asm);
        return asm.getDoor();
    }

    @Override
    public Item getItem(String name, String place) {
        var asm = new ItemAsm(name, place, asmList.getObjects());
        asmList.getItemAsmList().add(asm);
        return asm.getItem();
    }

    @Override
    public Dialog getDialog(String title, Dialog... dialogs) {
        var dialogAsm = new DialogAsm(title, dialogs);
        asmList.getDialogAsmList().add(dialogAsm);
        return dialogAsm.getDialogs().get(0);
    }
}