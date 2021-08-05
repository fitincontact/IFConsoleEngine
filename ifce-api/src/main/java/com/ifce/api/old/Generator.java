package com.ifce.api.old;

import com.ifce.api.old.main.core.Core;
import com.ifce.api.old.main.core.GeneratorCore;
import com.ifce.api.old.main.format.Format1;
import com.ifce.api.old.main.format.GeneratorFormat;
import com.ifce.api.old.main.format.PreFormat1;
import com.ifce.api.old.main.history.ItemHistory1;
import com.ifce.api.old.main.history.RoomHistory1;
import com.ifce.api.old.main.history.WayHistory1;
import com.ifce.api.old.main.object.*;
import com.ifce.api.old.main.utils.Print1;
import com.ifce.api.old.main.variable.GeneratorValuable;
import com.ifce.api.old.main.variable.Variable;

public class Generator {

    private final GeneratorObject generatorObject = new GeneratorObject();
    private final GeneratorCore generatorCore = new GeneratorCore();
    private final GeneratorFormat generatorFormat = new GeneratorFormat();
    private final RoomHistory1 roomHistory1 = RoomHistory1.getInstance();
    private final ItemHistory1 itemHistory1 = ItemHistory1.getInstance();
    private final WayHistory1 wayHistory1 = WayHistory1.getInstance();
    private final Variable variable = new GeneratorValuable().getVariable();

    public static void pl(final String s) {
        Print1.pl(s);
    }

    public static void p(final String s) {
        Print1.p(s);
    }

    //todo delete ? or stay for debug?
    public Game1 Game() {
        return generatorObject.newGame();
    }

    public Person1 Person(final String name) {
        return generatorObject.newPerson(name);
    }

    public Room1 Room(
            final String name,
            final String title,
            final String description
    ) throws Exception {
        final var room = generatorObject.newRoom(
                name,
                title,
                description
        );
        roomHistory1.add(room);
        return room;
    }

    public Item1 Item(
            final String word,
            final boolean isForInventory,
            final String invName,
            final String roomDescription,
            final String actRoomTxt,
            final String actInventoryTxt,
            final String useTxt
    ) throws Exception {
        final var item = generatorObject.newItem(
                word,
                isForInventory,
                invName,
                roomDescription,
                actRoomTxt,
                actInventoryTxt,
                useTxt
        );
        itemHistory1.add(item);
        return item;
    }

    public Way1 Way(
            final Room1 room1,
            final String wayTitle
    ) throws Exception {
        final var way = generatorObject.newWay(
                room1,
                wayTitle
        );
        wayHistory1.add(way);
        return way;
    }

    public Inventory1 Inventory() {
        return generatorObject.newInventory();
    }

    public Dialog1 Dialog(final String title) {
        return generatorObject.newDialog(title);
    }

    public Core Core(
            final Room1 room1,
            final Inventory1 inventory1
    ) {
        return generatorCore.newCore(
                room1,
                inventory1
        );
    }

    public PreFormat1 PreFormat() {
        return generatorFormat.newPreFormat();
    }

    public int setInstance(final PreFormat1 preFormat1) {
        return generatorFormat.setInstance(preFormat1);
    }

    public Format1 getInstance() {
        return generatorFormat.getInstance();
    }

//    public B Boolean(final boolean v) {
//        return variable.newBoolean(v);
//    }
//
//    public L Long(final long v) {
//        return variable.newLong(v);
//    }
//
//    public D Double(final double v) {
//        return variable.newDouble(v);
//    }
//
//    public S String(final String v) {
//        return variable.newString(v);
//    }

}