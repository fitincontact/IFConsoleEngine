package com.ifce.model.main;

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
}
