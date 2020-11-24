package com.fitincontact.engine.api;

import com.fitincontact.engine.main.core.Core;
import com.fitincontact.engine.main.core.GeneratorCore;
import com.fitincontact.engine.main.object.*;

public class Generator {

    private final GeneratorObject generatorObject = new GeneratorObject();
    private final GeneratorCore generatorCore = new GeneratorCore();

    public Person newPerson() {
        return generatorObject.newPerson();
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
            String word,
            final boolean isForInventory,
            String invName,
            String roomDescription,
            String actRoomTxt,
            String actInventoryTxt,
            String useTxt
    ) {
        return generatorObject.newItem(
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
            Room room,
            String wayTitle
    ) {
        return generatorObject.newWay(
                room,
                wayTitle
        );
    }

    public Inventory newInventory()
    {
        return generatorObject.newInventory();
    }

    public Core newCore(
            final Person person,
            final Room room,
            final Inventory inventory
    ){
        return generatorCore.newCore(
                person,
                room,
                inventory
        );
    }

}
