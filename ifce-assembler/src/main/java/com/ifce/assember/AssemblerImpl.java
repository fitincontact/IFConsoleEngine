package com.ifce.assember;

import com.ifce.model.assembler.singletons.*;
import com.ifce.model.singletons.Game;
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
    private final GameAsm gameAsm;
    private final Game game;

    public void assemble() {
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
                error(String.format("Assembler.addRooms: There is duplicate room name [%s]", asmName));
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
                error(String.format("Assembler.addItems: There is duplicate item name [%s]", asmName));
            } else {
                objects.add(item);
            }
        });
    }

    /**
     * Add object to pool see {@link Objects}
     */
    private void addDoors() {
        doorAsmList.getDoors().forEach(door -> {
            val asmName = door.getAsm().getName();
            if (objects.isExistsDoor(asmName)) {
                error(String.format("Assembler.addDoors: There is duplicate door name [%s]", asmName));
            } else {
                objects.add(door);
            }
        });
    }

    /**
     * Add object to pool see {@link Objects}
     */
    private void addDialogs() {
        dialogAsmList.getDialogs().forEach(dialog -> {
            val asmName = dialog.getAsm().getName();
            if (objects.isExistsDialog(asmName)) {
                error(String.format("Assembler.addDialogs: There is duplicate dialog name [%s]", asmName));
            } else {
                objects.add(dialog);
            }
        });
    }

    /**
     * Arrange items in rooms
     */
    private void bindingItems() {
        itemAsmList.getItems().forEach(item -> {
            val itemName = item.getAsm().getName();
            val asmPlaceName = item.getAsm().getPlace();
            var msgRoom = "";

            val room = objects.getRoom(asmPlaceName);
            if (room == null) {
                msgRoom = String.format(
                        "Assembler.bindingItems: For item name [%s] not found room name [%s]",
                        itemName,
                        asmPlaceName
                );
            } else {
                room.add(item);
            }

            var msgItem = "";
            if (!msgRoom.equals("")) {
                val itemPlace = objects.getItem(asmPlaceName);
                if (itemPlace == null) {
                    msgItem = String.format(
                            "Assembler.bindingItems: For item name [%s] not found item name [%s]",
                            itemName,
                            asmPlaceName
                    );
                } else {
                    itemPlace.add(item);
                }
            }

            if (!msgRoom.equals("") && !msgItem.equals("")) {
                error(msgRoom + "\n" + msgItem);
            }

        });
    }

    /**
     * Arrange doors in rooms
     */
    private void bindingDoors() {
        doorAsmList.getDoors().forEach(door -> {
            val asmDoorName = door.getAsm().getName();
            val asmRoomFrom = door.getAsm().getRoomFrom();
            val asmRoomTo = door.getAsm().getRoomTo();

            val roomTo = objects.getRoom(asmRoomTo);
            if (roomTo == null) {
                error(String.format(
                        "Assembler.bindingDoors: For door name [%s] not found roomTo name [%s]",
                        asmDoorName,
                        asmRoomTo
                ));
            }

            val roomFrom = objects.getRoom(asmRoomFrom);
            if (roomFrom == null) {
                error(String.format(
                        "Assembler.bindingDoors: For door name [%s] not found roomFrom name [%s]",
                        asmDoorName,
                        asmRoomFrom
                ));
            } else {
                roomFrom.add(door);
            }
        });
    }

    /**
     * Arrange dialogs in ...
     */
    private void bindingDialogs() {
    }

    /**
     * Building game state see {@link Game
     */
    private void gameProcess() {
        val player = objects.getItem(gameAsm.getPlayerName());
        if (player == null) {
            error("Assembler.gameProcess: Player is not created");
        } else {
            game.setPlayer(player);
            game.setAnnotation(gameAsm.getAnnotation());
            game.setCurrentRoom(game.getItemRoom(player));
        }
    }

    private void error(final String message) {
        throw new RuntimeException(String.format("Assembler RuntimeException: %s", message));
    }
}