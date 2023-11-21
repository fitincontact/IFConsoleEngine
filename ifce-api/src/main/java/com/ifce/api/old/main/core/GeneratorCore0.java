package com.ifce.api.old.main.core;

import com.ifce.api.old.main.history.RoomHistory0;
import com.ifce.api.old.main.object.Inventory0;
import com.ifce.api.old.main.object.Room0;

public class GeneratorCore0 {

    private final RoomHistory0 roomHistory0 = RoomHistory0.getInstance();

    public Core0 newCore(
            final Room0 room0,
            final Inventory0 inventory0
    ) {
        return new Core0(
                room0,
                inventory0
        );
    }
}