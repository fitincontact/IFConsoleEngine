package com.ifce.api.api;

import com.ifce.api.main.object.Inventory;
import com.ifce.api.main.object.Room;

public interface Enter {
    boolean apply(Room roomFrom, Inventory inventory);
}