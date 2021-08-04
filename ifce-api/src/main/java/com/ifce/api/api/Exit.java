package com.ifce.api.api;

import com.ifce.api.main.object.Inventory;
import com.ifce.api.main.object.Room;

public interface Exit {
    boolean apply(Room roomTo, Inventory inventory);
}