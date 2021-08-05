package com.ifce.api.old.main.core;

import com.ifce.api.old.main.history.RoomHistory1;
import com.ifce.api.old.main.object.Inventory1;
import com.ifce.api.old.main.object.Room1;

public class GeneratorCore {

    private final RoomHistory1 roomHistory1 = RoomHistory1.getInstance();

    public Core newCore(
            final Room1 room1,
            final Inventory1 inventory1
    ) {
        return new Core(
                room1,
                inventory1
        );
    }
}