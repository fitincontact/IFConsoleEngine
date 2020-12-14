package com.fitincontact.engine.api;

import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Room;

public interface Enter {
    boolean apply(Room roomFrom, Inventory inventory);
}