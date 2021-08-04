package com.ifce.api.api;

import com.ifce.api.main.object.Inventory;
import com.ifce.api.main.object.Room;

import java.io.IOException;

public interface Act {
    boolean apply(Room room, Inventory inventory) throws IOException, ClassNotFoundException;
}