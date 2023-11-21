package com.ifce.api.old;

import com.ifce.api.old.main.core.Core0;
import com.ifce.api.old.main.core.GeneratorCore0;
import com.ifce.api.old.main.format.Format0;
import com.ifce.api.old.main.format.GeneratorFormat0;
import com.ifce.api.old.main.format.PreFormat0;
import com.ifce.api.old.main.history.ItemHistory0;
import com.ifce.api.old.main.history.RoomHistory0;
import com.ifce.api.old.main.history.WayHistory0;
import com.ifce.api.old.main.object.*;
import com.ifce.api.old.main.utils.Print0;

public class Generator0 {

    private final GeneratorObject0 generatorObject0 = new GeneratorObject0();
    private final GeneratorCore0 generatorCore0 = new GeneratorCore0();
    private final GeneratorFormat0 generatorFormat0 = new GeneratorFormat0();
    private final RoomHistory0 roomHistory0 = RoomHistory0.getInstance();
    private final ItemHistory0 itemHistory0 = ItemHistory0.getInstance();
    private final WayHistory0 wayHistory0 = WayHistory0.getInstance();

    public static void pl(final String s) {
        Print0.pl(s);
    }

    public static void p(final String s) {
        Print0.p(s);
    }

    //todo delete ? or stay for debug?
    public Game0 Game() {
        return generatorObject0.newGame();
    }

    public Person0 Person(final String name) {
        return generatorObject0.newPerson(name);
    }

    public Room0 Room(
            final String name,
            final String title,
            final String description
    ) throws Exception {
        final var room = generatorObject0.newRoom(
                name,
                title,
                description
        );
        roomHistory0.add(room);
        return room;
    }

    public Item0 Item(
            final String word,
            final boolean isForInventory,
            final String invName,
            final String roomDescription,
            final String actRoomTxt,
            final String actInventoryTxt,
            final String useTxt
    ) throws Exception {
        final var item = generatorObject0.newItem(
                word,
                isForInventory,
                invName,
                roomDescription,
                actRoomTxt,
                actInventoryTxt,
                useTxt
        );
        itemHistory0.add(item);
        return item;
    }

    public Way0 Way(
            final Room0 room0,
            final String wayTitle
    ) throws Exception {
        final var way = generatorObject0.newWay(
                room0,
                wayTitle
        );
        wayHistory0.add(way);
        return way;
    }

    public Inventory0 Inventory() {
        return generatorObject0.newInventory();
    }

    public Dialog0 Dialog(final String title) {
        return generatorObject0.newDialog(title);
    }

    public Core0 Core(
            final Room0 room0,
            final Inventory0 inventory0
    ) {
        return generatorCore0.newCore(
                room0,
                inventory0
        );
    }

    public PreFormat0 PreFormat() {
        return generatorFormat0.newPreFormat();
    }

    public int setInstance(final PreFormat0 preFormat0) {
        return generatorFormat0.setInstance(preFormat0);
    }

    public Format0 getInstance() {
        return generatorFormat0.getInstance();
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