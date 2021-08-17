package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.dialog.Dialog;
import com.ifce.model.Door;
import com.ifce.model.Item;
import com.ifce.model.Room;
import com.ifce.model.asm.DoorAsm;
import com.ifce.model.asm.ItemAsm;
import com.ifce.model.asm.RoomAsm;
import com.ifce.model.asm.singletons.DialogsAsm;
import com.ifce.model.asm.singletons.DoorsAsm;
import com.ifce.model.asm.singletons.ItemsAsm;
import com.ifce.model.asm.singletons.RoomsAsm;
import com.ifce.model.etc.Game;
import com.ifce.service.Assembler;
import com.ifce.service.EngineService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IFCEServiceImpl implements IFCEService {
    private final DialogsAsm dialogsAsm;
    private final DoorsAsm doorsAsm;
    private final ItemsAsm itemsAsm;
    private final RoomsAsm roomsAsm;
    private final Game game;
    private final Assembler assembler;
    private final EngineService engineService;

    @Override
    public Dialog dialog(
            String title,
            Dialog[]... dialogs
    ) {
        val dialog = new Dialog(title, dialogs);
        dialogsAsm.add(dialog);
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
            String roomStrFirst,
            String doorStrFirst,
            String roomStrSecond,
            String doorStrSecond
    ) {
        val asm = new DoorAsm(name, roomStrFirst, doorStrFirst, roomStrSecond, doorStrSecond);
        val door = new Door(asm);
        doorsAsm.add(door);
        return door;
    }

    @Override
    public Item item(
            String name,
            String place
    ) {
        val asm = new ItemAsm(name, place);
        val item = new Item(asm);
        itemsAsm.add(item);
        return item;
    }

    @Override
    public Room room(String name) {
        val asm = new RoomAsm(name);
        val room = new Room(asm);
        roomsAsm.add(room);
        return room;
    }

    @Override
    public Game game(String playerName, String annotation) {
        return game;
    }

    @Override
    public void start() {
        assembler.assemble();
        engineService.start();
    }
}