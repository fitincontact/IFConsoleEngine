package com.ifce.api.api;

import com.ifce.api.main.object.Inventory;
import com.ifce.api.main.object.Room;

public interface Phrase {
    void apply(Room room, Inventory inventory);
}