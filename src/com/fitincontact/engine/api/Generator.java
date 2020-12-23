package com.fitincontact.engine.api;

import com.fitincontact.engine.main.core.Core;
import com.fitincontact.engine.main.core.GeneratorCore;
import com.fitincontact.engine.main.format.Format;
import com.fitincontact.engine.main.format.GeneratorFormat;
import com.fitincontact.engine.main.format.PreFormat;
import com.fitincontact.engine.main.history.ItemHistory;
import com.fitincontact.engine.main.history.RoomHistory;
import com.fitincontact.engine.main.object.*;
import com.fitincontact.engine.main.utils.Utils;
import com.fitincontact.engine.main.variable.*;

public class Generator {

    private final GeneratorObject generatorObject = new GeneratorObject();
    private final GeneratorCore generatorCore = new GeneratorCore();
    private final GeneratorFormat generatorFormat = new GeneratorFormat();
    private final RoomHistory roomHistory = RoomHistory.getInstance();
    private final ItemHistory itemHistory = ItemHistory.getInstance();
    private final Variable variable = new GeneratorValuable().getVariable();

    public static void pl(final String s) {
        Utils.pl(s);
    }

    public static void p(final String s) {
        Utils.p(s);
    }

    //todo delete ? or stay for debug?
    public Game newGame() {
        return generatorObject.newGame();
    }

    public Person newPerson(final String name) {
        return generatorObject.newPerson(name);
    }

    public Room newRoom(
            final String name,
            final String title,
            final String description
    ) throws Exception {
        final Room room = generatorObject.newRoom(
                name,
                title,
                description
        );
        roomHistory.add(room);
        return room;
    }

    public Item newItem(
            final String word,
            final boolean isForInventory,
            final String invName,
            final String roomDescription,
            final String actRoomTxt,
            final String actInventoryTxt,
            final String useTxt
    ) throws Exception {
        final Item item = generatorObject.newItem(
                word,
                isForInventory,
                invName,
                roomDescription,
                actRoomTxt,
                actInventoryTxt,
                useTxt
        );
        itemHistory.add(item);
        return item;
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
            final Room room,
            final Inventory inventory
    ) {
        return generatorCore.newCore(
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

    public B newBoolean(final boolean v) {
        return variable.newBoolean(v);
    }

    public L newLong(final long v) {
        return variable.newLong(v);
    }

    public D newDouble(final double v) {
        return variable.newDouble(v);
    }

    public S newString(final String v) {
        return variable.newString(v);
    }

}