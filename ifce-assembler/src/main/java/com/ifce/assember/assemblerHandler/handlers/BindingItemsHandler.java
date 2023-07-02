package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.ItemAsm;
import com.ifce.assember.model.singletons.AsmList;
import com.ifce.model.common.Item;
import com.ifce.model.common.Room;
import com.ifce.model.common.enums.PlaceType;
import com.ifce.util.cor.CoRHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.ifce.assember.assemblerHandler.handlers.AssemblerHandler.throwError;

/**
 * Arrange items in rooms and in items
 */
@RequiredArgsConstructor
@Component
public class BindingItemsHandler implements CoRHandler {
    private final AsmList asmList;

    @Override
    public void exec() {
        asmList.getItemAsmList().getItemAsms().forEach(itemAsm -> {
            var asmPlaceName = itemAsm.getPlace();
            var room = asmList.getObjects().getRoom(asmPlaceName);
            var item = asmList.getObjects().getItem(asmPlaceName);
            checkError(room, item, itemAsm);
            place(room, itemAsm);
            place(item, itemAsm);
        });
    }

    private void checkError(final Room room, final Item item, final ItemAsm itemAsm) {
        if (room == null && item == null) {
            var itemName = itemAsm.getName();
            var asmPlaceName = itemAsm.getPlace();
            var msgRoom = String.format("Assembler.bindingItems: For item name [%s] not found room name [%s]", itemName, asmPlaceName);
            var msgItem = String.format("Assembler.bindingItems: For item name [%s] not found item name [%s]", itemName, asmPlaceName);
            throwError(msgRoom + "\n" + msgItem);
        }
    }

    private void place(Room room, ItemAsm itemAsm) {
        if (room != null) {
            var item = itemAsm.getItem();
            item.setPlace(room.getName());
            item.setPlaceType(PlaceType.ROOM);
            room.add(item);
        }
    }

    private void place(Item itemPlace, ItemAsm itemAsm) {
        if (itemPlace != null) {
            var item = itemAsm.getItem();
            var players = getPlayers();
            players.forEach(player -> {
                if (player == item) {
                    throwError(String.format("Assembler.BindingItems: Player [%s] cant place in item [%s] (only to room)", item.getName(), itemPlace.getName()));
                }
            });

            var placeType = definePlaceType(itemPlace);
            item.setPlace(itemPlace.getName());
            item.setPlaceType(placeType);
            itemPlace.add(item);
        }
    }

    private PlaceType definePlaceType(Item itemPlace) {
        AtomicBoolean isInventory = new AtomicBoolean(false);
        var players = getPlayers();
        players.forEach(player -> {
            if (itemPlace.getName().equals(player.getName())) {
                isInventory.set(true);
            }
        });

        if (isInventory.get()) {
            return PlaceType.INVENTORY;
        }
        return PlaceType.ITEM;
    }

    private List<Item> getPlayers() {
        var names = asmList.getGameAsm().getPlayerNames();
        return names.stream().map(name -> asmList.getItemAsmList().getItem(name)).toList();
    }
}