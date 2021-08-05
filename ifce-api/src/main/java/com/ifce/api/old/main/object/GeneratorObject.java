package com.ifce.api.old.main.object;

import com.ifce.api.old.main.history.DialogHistory1;

public class GeneratorObject {

    private final DialogHistory1 dialogHistory1 = DialogHistory1.getInstance();

    public Game1 newGame() {
        return Game1.getInstance();
    }

    public Person1 newPerson(final String name) {
        return new Person1(name);
    }

    public Room1 newRoom(
            final String name,
            final String title,
            final String description
    ) {
        return new Room1(
                name,
                title,
                description
        );
    }

    public Item1 newItem(
            final String word,
            final boolean isForInventory,
            final String invName,
            final String roomDescription,
            final String actRoomTxt,
            final String actInventoryTxt,
            final String useTxt
    ) {
        return new Item1(
                word,
                isForInventory,
                invName,
                roomDescription,
                actRoomTxt,
                actInventoryTxt,
                useTxt
        );
    }

    public Way1 newWay(
            final Room1 room1,
            final String wayTitle
    ) {
        return new Way1(
                room1,
                wayTitle
        );
    }

    public Inventory1 newInventory() {
        return new Inventory1();
    }

    public Dialog1 newDialog(final String title) {
        final var dialog = new Dialog1(title);
        dialog.setUniqueLong(dialogHistory1.getDialogs().size());
        return dialog;
    }
}