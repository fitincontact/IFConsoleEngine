package com.ifce.api.old.main.object;

import com.ifce.api.old.main.history.DialogHistory0;

public class GeneratorObject0 {

    private final DialogHistory0 dialogHistory0 = DialogHistory0.getInstance();

    public Game0 newGame() {
        return Game0.getInstance();
    }

    public Person0 newPerson(final String name) {
        return new Person0(name);
    }

    public Room0 newRoom(
            final String name,
            final String title,
            final String description
    ) {
        return new Room0(
                name,
                title,
                description
        );
    }

    public Item0 newItem(
            final String word,
            final boolean isForInventory,
            final String invName,
            final String roomDescription,
            final String actRoomTxt,
            final String actInventoryTxt,
            final String useTxt
    ) {
        return new Item0(
                word,
                isForInventory,
                invName,
                roomDescription,
                actRoomTxt,
                actInventoryTxt,
                useTxt
        );
    }

    public Way0 newWay(
            final Room0 room0,
            final String wayTitle
    ) {
        return new Way0(
                room0,
                wayTitle
        );
    }

    public Inventory0 newInventory() {
        return new Inventory0();
    }

    public Dialog0 newDialog(final String title) {
        final var dialog = new Dialog0(title);
        dialog.setUniqueLong(dialogHistory0.getDialogs().size());
        return dialog;
    }
}