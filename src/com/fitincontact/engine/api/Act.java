package com.fitincontact.engine.api;

import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Room;

import java.io.IOException;

public interface Act {
    boolean apply(Room room, Inventory inventory) throws IOException, ClassNotFoundException;
}