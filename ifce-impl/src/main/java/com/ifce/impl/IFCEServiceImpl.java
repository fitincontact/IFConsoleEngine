package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.assember.model.DialogAsm;
import com.ifce.assember.model.DoorAsm;
import com.ifce.assember.model.ItemAsm;
import com.ifce.assember.model.RoomAsm;
import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.main.Dialog;
import com.ifce.model.main.Door;
import com.ifce.model.main.Item;
import com.ifce.model.main.Room;
import com.ifce.model.singletons.State;
import com.ifce.service.AssemblerService;
import com.ifce.service.EngineService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IFCEServiceImpl implements IFCEService {
    private final AsmList asmList;
    private final AssemblerService assemblerService;
    private final EngineService engineService;
    private final State state;

    @Override
    public Dialog dialog(
            String title,
            Dialog... dialogs
    ) {
        val dialogAsm = new DialogAsm(title, dialogs);
        asmList.getDialogAsmList().add(dialogAsm);
        return dialogAsm.getDialogs().get(0);
    }

    @Override
    public Dialog dialog(
            String request,
            String response,
            Dialog... dialogs
    ) {
        return new Dialog(request, response, dialogs);
    }

    @Override
    public Door door(
            String name,
            String roomFrom,
            String roomTo
    ) {
        val asm = new DoorAsm(name, roomFrom, roomTo);
        asmList.getDoorAsmList().add(asm);
        return asm.getDoor();
    }

    @Override
    public Item item(
            String name,
            String place
    ) {
        val asm = new ItemAsm(name, place, asmList.getObjects());
        asmList.getItemAsmList().add(asm);
        return asm.getItem();
    }

    @Override
    public Room room(String name) {
        val asm = new RoomAsm(name);
        asmList.getRoomAsmList().add(asm);
        return asm.getRoom();
    }

    @Override
    public void story(String playerName, String annotation) {
        asmList.getGameAsm().setPlayerName(playerName);
        asmList.getGameAsm().setAnnotation(annotation);
    }

    @Override
    public void start() {
        assemblerService.assemble();
        //todo move init to engineService?
        state.init();
        engineService.start();
    }
}