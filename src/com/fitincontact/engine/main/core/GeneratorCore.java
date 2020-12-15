package com.fitincontact.engine.main.core;

import com.fitincontact.engine.main.object.Game;
import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Room;
import com.fitincontact.engine.main.history.RoomHistory;

public class GeneratorCore {

    private final RoomHistory roomHistory = RoomHistory.getInstance();

    public Core newCore(
            final Game game,
            final Room room,
            final Inventory inventory
    ) {
        return new Core(
                game,
                room,
                inventory
        );
    }
}