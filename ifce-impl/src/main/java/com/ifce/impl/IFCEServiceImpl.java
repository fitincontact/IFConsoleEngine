package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.model.Dialog;
import com.ifce.model.Door;
import com.ifce.model.Item;
import com.ifce.model.Room;
import com.ifce.model.asm.DoorAsm;
import com.ifce.model.asm.ItemAsm;
import com.ifce.model.asm.RoomAsm;
import com.ifce.model.asm.singletons.*;
import com.ifce.service.Assembler;
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
    private final Assembler assembler;
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
            String room
    ) {
        val asm = new DoorAsm(name, room);
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
        assembler.assemble();
        engineService.start();
    }
}