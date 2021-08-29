package com.ifce.assember.assemblerHandler.handlers;

import com.ifce.model.assembler.singletons.ItemAsmList;
import com.ifce.model.singletons.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Arrange items in rooms
 */
@RequiredArgsConstructor
@Component
public class BindingItemsHandler implements AssemblerHandler {
    private final Objects objects;
    private final ItemAsmList itemAsmList;

    @Override
    public void exec() {
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
}