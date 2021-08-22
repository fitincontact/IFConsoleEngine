package com.ifce.assember;

import com.ifce.model.asm.singletons.DialogAsmList;
import com.ifce.model.asm.singletons.DoorAsmList;
import com.ifce.model.asm.singletons.ItemAsmList;
import com.ifce.model.asm.singletons.RoomAsmList;
import com.ifce.model.etc.Game;
import com.ifce.model.singletons.Objects;
import com.ifce.service.Assembler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AssemblerImpl implements Assembler {
    private final DialogAsmList dialogAsmList;
    private final DoorAsmList doorAsmList;
    private final ItemAsmList itemAsmList;
    private final RoomAsmList roomAsmList;
    private final Objects objects;
    private final Game game;

    public void assemble() {
        //Print.pl("assemble");
        addRooms();
        addItems();
        addDoors();
        addDialogs();
        bindingItems();
        bindingDoors();
        bindingDialogs();
        gameProcess();
    }

    /**
     * Add object to pool see {@link Objects}
     */
    private void addRooms() {
        roomAsmList.getRooms().forEach(room -> {
            val asmName = room.getAsm().getName();
            if (objects.isExistsRoom(asmName)) {
                error(String.format("Assembler.roomsProcess: There is duplicate room name %s ", asmName));
            } else {
                objects.add(room);
            }
        });
    }

    /**
     * Add object to pool see {@link Objects}
     */
    private void addItems() {
        itemAsmList.getItems().forEach(item -> {
            val asmName = item.getAsm().getName();
            if (objects.isExistsItem(asmName)) {
                error(String.format("Assembler.itemsProcess: There is duplicate item name %s ", asmName));
            } else {
                objects.add(item);
            }
        });
    }

    /**
     * Add object to pool see {@link Objects}
     */
    private void addDoors() {
    }

    /**
     * Add object to pool see {@link Objects}
     */
    private void addDialogs() {
    }

    /**
     * Arrange items in rooms
     */
    private void bindingItems() {
    }

    /**
     * Arrange doors in rooms
     */
    private void bindingDoors() {
    }

    /**
     * Arrange dialogs in ...
     */
    private void bindingDialogs() {
    }

    /**
     * Building game state see {@link Game}
     */
    private void gameProcess() {
        //current room is room where item player placed
    }

    private void error(final String message) {
        game.end(message);
    }
}