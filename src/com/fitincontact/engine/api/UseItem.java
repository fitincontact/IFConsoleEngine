package com.fitincontact.engine.api;

import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Room;

public interface UseItem {
    void apply(Room room, Inventory inventory);
}
