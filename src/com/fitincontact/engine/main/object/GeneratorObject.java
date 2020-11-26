package com.fitincontact.engine.main.object;


import com.fitincontact.engine.api.Act;
import com.fitincontact.engine.api.Use;

public class GeneratorObject {

    public Game newPerson() {
        return new Game();
    }

    public Room newRoom(
            final String name,
            final String title,
            final String description
    ) {
        return new Room(
                name,
                title,
                description
        );
    }

    public Item newItem(
            final String word,
            final boolean isForInventory,
            final String invName,
            final String roomDescription,
            final String actRoomTxt,
            final String actInventoryTxt,
            final String useTxt,
            final Act act,
            final Use use
    ) {
        return new Item(
                word,
                isForInventory,
                invName,
                roomDescription,
                actRoomTxt,
                actInventoryTxt,
                useTxt,
                act,
                use
        );
    }

    public Way newWay(
            final Room room,
            final String wayTitle
    ) {
        return new Way(
                room,
                wayTitle
        );
    }

    public Inventory newInventory() {
        return new Inventory();
    }

}
