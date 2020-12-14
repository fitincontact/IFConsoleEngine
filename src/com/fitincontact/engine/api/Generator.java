package com.fitincontact.engine.api;

import com.fitincontact.engine.main.core.Core;
import com.fitincontact.engine.main.core.GeneratorCore;
import com.fitincontact.engine.main.format.Format;
import com.fitincontact.engine.main.format.GeneratorFormat;
import com.fitincontact.engine.main.format.PreFormat;
import com.fitincontact.engine.main.object.*;

public class Generator {

    private final GeneratorObject generatorObject = new GeneratorObject();
    private final GeneratorCore generatorCore = new GeneratorCore();
    private final GeneratorFormat generatorFormat = new GeneratorFormat();

    public Game newGame(final Person person) {
        return generatorObject.newGame(person);
    }

    public Person newPerson(final String name) {
        return generatorObject.newPerson(name);
    }

    public Room newRoom(
            final String name,
            final String title,
            final String description
    ) {
        return generatorObject.newRoom(
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
        return generatorObject.newItem(
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
        return generatorObject.newWay(
                room,
                wayTitle
        );
    }

    public Inventory newInventory() {
        return generatorObject.newInventory();
    }

    public Core newCore(
            final Game game,
            final Room room,
            final Inventory inventory
    ) {
        return generatorCore.newCore(
                game,
                room,
                inventory
        );
    }

    public PreFormat newPreFormat() {
        return generatorFormat.newPreFormat();
    }

    public int setInstance(final PreFormat preFormat) {
        return generatorFormat.setInstance(preFormat);
    }

    public Format getInstance() {
        return generatorFormat.getInstance();
    }

}