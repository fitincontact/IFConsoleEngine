package com.fitincontact.engine.main.object;

import com.fitincontact.engine.main.history.DialogHistory;

public class GeneratorObject {

    private final DialogHistory dialogHistory = DialogHistory.getInstance();

    public Game newGame() {
        return Game.getInstance();
    }

    public Person newPerson(final String name) {
        return new Person(name);
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
            final String useTxt
    ) {
        return new Item(
                word,
                isForInventory,
                invName,
                roomDescription,
                actRoomTxt,
                actInventoryTxt,
                useTxt
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

    public Dialog newDialog(final String title) {
        final Dialog dialog = new Dialog(title);
        dialog.setUniqueLong(dialogHistory.getDialogs().size());
        return dialog;
    }
}