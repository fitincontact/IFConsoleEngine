package com.fitincontact.engine.api;

import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Item;
import com.fitincontact.engine.main.object.Room;

import java.util.List;

public interface Use {
    boolean apply(Room room, List<Item> items);
}
