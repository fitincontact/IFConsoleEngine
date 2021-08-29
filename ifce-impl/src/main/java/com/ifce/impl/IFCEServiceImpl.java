package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.model.assembler.DoorAsm;
import com.ifce.model.assembler.ItemAsm;
import com.ifce.model.assembler.RoomAsm;
import com.ifce.model.assembler.singletons.*;
import com.ifce.model.main.Dialog;
import com.ifce.model.main.Door;
import com.ifce.model.main.Item;
import com.ifce.model.main.Room;
import com.ifce.service.AssemblerService;
import com.ifce.service.EngineService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IFCEServiceImpl implements IFCEService {
    private final DialogAsmList dialogAsmList;
    private final DoorAsmList doorAsmList;
    private final ItemAsmList itemAsmList;
    private final RoomAsmList roomAsmList;
    private final GameAsm gameAsm;
    private final AssemblerService assemblerService;
    private final EngineService engineService;

    @Override
    public Dialog dialog(
            String title,
            Dialog[]... dialogs
    ) {
        val dialog = new Dialog(title, dialogs);
        dialog.add(title);
        dialogAsmList.add(dialog);
        return dialog;
    }

    @Override
    public Dialog dialog(
            String request,
            String response,
            Dialog[]... dialogs
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
        val door = new Door(asm);
        door.add(name);
        doorAsmList.add(door);
        return door;
    }

    @Override
    public Item item(
            String name,
            String place
    ) {
        val asm = new ItemAsm(name, place);
        val item = new Item(asm);
        item.add(name);
        itemAsmList.add(item);
        return item;
    }

    @Override
    public Room room(String name) {
        val asm = new RoomAsm(name);
        val room = new Room(asm);
        room.add(name);
        roomAsmList.add(room);
        return room;
    }

    @Override
    public void story(String playerName, String annotation) {
        gameAsm.setPlayerName(playerName);
        gameAsm.setAnnotation(annotation);
    }

    @Override
    public void start() {
        assemblerService.assemble();
        engineService.start();
    }
}