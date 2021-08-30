package com.ifce.assember.model;

import com.ifce.model.main.Door;
import lombok.Data;

@Data
public class DoorAsm {
    private final String name;
    private final String roomFrom;
    private final String roomTo;
    private final Door door;

    public DoorAsm(
            String name,
            String roomFrom,
            String roomTo
    ) {
        this.name = name;
        this.roomFrom = roomFrom;
        this.roomTo = roomTo;
        door = new Door(name);
    }
}