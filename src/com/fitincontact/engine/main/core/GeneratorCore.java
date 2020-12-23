package com.fitincontact.engine.main.core;

import com.fitincontact.engine.main.history.RoomHistory;
import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Room;

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