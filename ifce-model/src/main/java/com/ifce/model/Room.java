package com.ifce.model;

import com.ifce.model.etc.Word;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Room {
    private final Word word;
    private final List<Item> items = new ArrayList<>();
    private final List<Door> doors = new ArrayList<>();

    public Room add(final String word) {
        this.word.add(word);
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
