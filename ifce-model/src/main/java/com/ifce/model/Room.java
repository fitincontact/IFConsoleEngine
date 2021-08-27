package com.ifce.model;

import com.ifce.model.asm.RoomAsm;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Room extends ObjectAbstract {
    private final RoomAsm asm;

    private List<Item> items = new ArrayList<>();
    private List<Door> doors = new ArrayList<>();

    public Room add(final Item item) {
        items.add(item);
        return this;
    }

    public Room add(final Door door) {
        doors.add(door);
        return this;
    }
}
