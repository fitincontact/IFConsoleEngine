package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.assember.model.ItemAsm;
import com.ifce.assember.model.singletons.GameAsm;
import com.ifce.assember.model.singletons.ItemAsmList;
import com.ifce.model.main.Item;
import com.ifce.model.main.Room;
import com.ifce.model.main.enums.PlaceType;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Arrange items in rooms and in items
 */
@RequiredArgsConstructor
@Component
public class BindingItemsHandler implements AssemblerHandler {
    private final Objects objects;
    private final ItemAsmList itemAsmList;
    private final GameAsm gameAsm;

    @Override
    public void exec() {
        itemAsmList.getItemAsms().forEach(itemAsm -> {
            val asmPlaceName = itemAsm.getPlace();
            val room = objects.getRoom(asmPlaceName);
            val item = objects.getItem(asmPlaceName);
            checkError(room, item, itemAsm);
            place(room, itemAsm);
            place(item, itemAsm);
        });
    }

    private void checkError(
            final Room room,
            final Item item,
            final ItemAsm itemAsm
    ) {
        if (room == null && item == null) {
            val itemName = itemAsm.getName();
            val asmPlaceName = itemAsm.getPlace();
            val msgRoom = String.format(
                    "Assembler.bindingItems: For item name [%s] not found room name [%s]",
                    itemName,
                    asmPlaceName
            );
            val msgItem = String.format(
                    "Assembler.bindingItems: For item name [%s] not found item name [%s]",
                    itemName,
                    asmPlaceName
            );
            error(msgRoom + "\n" + msgItem);
        }
    }

    private void place(Room room, ItemAsm itemAsm) {
        if (room != null) {
            val item = itemAsm.getItem();
            item.setPlace(room.getName());
            item.setPlaceType(PlaceType.ROOM);
            room.add(item);
        }
    }

    private void place(Item itemPlace, ItemAsm itemAsm) {
        if (itemPlace != null) {
            val item = itemAsm.getItem();
            val player = getPlayer();
            if (player == item) {
                error(
                        String.format("Assembler.BindingItems: Player [%s] cant place in item [%s] (only to room)",
                                item.getName(),
                                itemPlace.getName()
                        )
                );
            }
            val placeType = definePlaceType(itemPlace);
            item.setPlace(itemPlace.getName());
            item.setPlaceType(placeType);
            itemPlace.add(item);
        }
    }

    private PlaceType definePlaceType(Item itemPlace) {
        val player = getPlayer();
        if (itemPlace.getName().equals(player.getName())) {
            return PlaceType.INVENTORY;
        }
        return PlaceType.ITEM;
    }

    private Item getPlayer() {
        return itemAsmList.getItem(gameAsm.getPlayerName());
    }
}