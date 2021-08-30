package com.ifce.assember.model;

import com.ifce.model.main.Room;
import lombok.Data;

@Data
public class RoomAsm {
    private final String name;
    private final Room room;

    public RoomAsm(final String name) {
        this.name = name;
        room = new Room(name);
    }
}