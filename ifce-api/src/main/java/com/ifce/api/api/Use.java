package com.ifce.api.api;

import com.ifce.api.main.object.Item;
import com.ifce.api.main.object.Room;

import java.util.List;

public interface Use {
    boolean apply(Room room, List<Item> items);
}