package com.ifce.api.old;

import com.ifce.api.old.main.object.Inventory1;
import com.ifce.api.old.main.object.Room1;

import java.io.IOException;

public interface Act1 {
    boolean apply(Room1 room1, Inventory1 inventory1) throws IOException, ClassNotFoundException;
}