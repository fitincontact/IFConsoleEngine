package com.ifce.api.api;

import com.ifce.api.main.core.Core;
import com.ifce.api.main.core.GeneratorCore;
import com.ifce.api.main.format.Format;
import com.ifce.api.main.format.GeneratorFormat;
import com.ifce.api.main.format.PreFormat;
import com.ifce.api.main.history.ItemHistory;
import com.ifce.api.main.history.RoomHistory;
import com.ifce.api.main.history.WayHistory;
import com.ifce.api.main.object.*;
import com.ifce.api.main.utils.Print;
import com.ifce.api.main.variable.GeneratorValuable;
import com.ifce.api.main.variable.Variable;

public class Generator {

    private final GeneratorObject generatorObject = new GeneratorObject();
    private final GeneratorCore generatorCore = new GeneratorCore();
    private final GeneratorFormat generatorFormat = new GeneratorFormat();
    private final RoomHistory roomHistory = RoomHistory.getInstance();
    private final ItemHistory itemHistory = ItemHistory.getInstance();
    private final WayHistory wayHistory = WayHistory.getInstance();
    private final Variable variable = new GeneratorValuable().getVariable();

    public static void pl(final String s) {
        Print.pl(s);
    }

    public static void p(final String s) {
        Print.p(s);
    }

    //todo delete ? or stay for debug?
    public Game Game() {
        return generatorObject.newGame();
    }

    public Person Person(final String name) {
        return generatorObject.newPerson(name);
    }

    public Room Room(
            final String name,
            final String title,
            final String description
    ) throws Exception {
        final var room = generatorObject.newRoom(
                name,
                title,
                description
        );
        roomHistory.add(room);
        return room;
    }

    public Item Item(
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
        itemHistory.add(item);
        return item;
    }

    public Way Way(
            final Room room,
            final String wayTitle
    ) throws Exception {
        final var way = generatorObject.newWay(
                room,
                wayTitle
        );
        wayHistory.add(way);
        return way;
    }

    public Inventory Inventory() {
        return generatorObject.newInventory();
    }

    public Dialog Dialog(final String title) {
        return generatorObject.newDialog(title);
    }

    public Core Core(
            final Room room,
            final Inventory inventory
    ) {
        return generatorCore.newCore(
                room,
                inventory
        );
    }

    public PreFormat PreFormat() {
        return generatorFormat.newPreFormat();
    }

    public int setInstance(final PreFormat preFormat) {
        return generatorFormat.setInstance(preFormat);
    }

    public Format getInstance() {
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