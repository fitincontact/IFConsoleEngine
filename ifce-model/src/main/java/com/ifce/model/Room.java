package com.ifce.model;

import com.ifce.model.asm.RoomAsm;
import com.ifce.model.etc.Word;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Room {
    private final RoomAsm asm;

    private Word name;
    private List<Item> items = new ArrayList<>();
    private List<Door> doors = new ArrayList<>();

    public Room add(final String word) {
        this.name.add(word);
        return this;
    }

    public Room add(final Item item) {
        items.add(item);
        return this;
    }

    public Room add(final Door door) {
        doors.add(door);
        return this;
    }
}
