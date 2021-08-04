package com.ifce.api.main.core;

import com.ifce.api.main.history.RoomHistory;
import com.ifce.api.main.object.Inventory;
import com.ifce.api.main.object.Room;

public class GeneratorCore {

    private final RoomHistory roomHistory = RoomHistory.getInstance();

    public Core newCore(
            final Room room,
            final Inventory inventory
    ) {
        return new Core(
                room,
                inventory
        );
    }
}