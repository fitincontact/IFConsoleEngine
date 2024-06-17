package com.api.model.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Room extends ObjectAbstract {
    private List<Item> items = new ArrayList<>();
    private List<Door> doors = new ArrayList<>();

    public Room(final String name) {
        super.add(name);
    }

    public Room add(final Item item) {
        items.add(item);
        return this;
    }

    public Room add(final Door door) {
        doors.add(door);
        return this;
    }

    public boolean contains(final Item item) {
        return items.contains(item);
    }

    public Iterable<Way> getWays() {
        return null;
    }

    public boolean exit(Room room, Inventory inventoryCurrent) {
        return false;
    }

    public List<String> getExitTxt() {
        return null;
    }

    public boolean enter(Room roomCurrent, Inventory inventoryCurrent) {
        return false;
    }

    public List<String> getEnterTxt() {
        return null;
    }

    public List<String> toStrRoom(Inventory inventoryCurrent) {
        return null;
    }

    public String roomAndInventoryString(Inventory inventoryCurrent) {
        return null;
    }
}
