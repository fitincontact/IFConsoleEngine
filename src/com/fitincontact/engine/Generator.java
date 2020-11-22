package com.fitincontact.engine;

public class Generator {

    public Generator() {
    }

    public Room newRoom(
            String name,
            String title,
            String description
    ) {
        return new Room(
                name,
                title,
                description
        );
    }

    public Item newItem(
            String word,
            final boolean isForInventory,
            String invName,
            String roomDescription,
            String actText,
            String takeText,
            String useText
    ) {
        return new Item(
                word,
                isForInventory,
                invName,
                roomDescription,
                actText,
                takeText,
                useText
        );
    }

    public Way newWay(
            Room room,
            String wayTitle
    ) {
        return new Way(
                room,
                wayTitle
        );
    }

    public Inventory newInventory()
    {
        return new Inventory();
    }

    public Core newCore(
            Room room,
            Inventory inventory
    ){
        return new Core(
                 room,
                 inventory
        );
    }

}
