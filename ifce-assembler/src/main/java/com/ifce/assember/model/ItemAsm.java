package com.ifce.assember.model;

import com.ifce.assember.assemblerHandler.AssemblerHandlerService;
import com.ifce.model.common.Item;
import com.ifce.model.singletons.Objects;
import lombok.Data;

/**
 * Assembling date for items
 * Use in {@link AssemblerHandlerService} for assembling
 */
@Data
public class ItemAsm {
    /**
     * Map to {@link Objects#items}
     */
    @SuppressWarnings("JavadocReference")
    private final String name;
    /**
     * Room or item where this item is placed
     */
    private final String room;
    /**
     * General game object for game engine
     */
    private final Item item;

    public ItemAsm(
            final String name,
            final String room,
            final Objects objects
    ) {
        this.name = name;
        this.room = room;
        item = new Item(name, objects);
    }
}