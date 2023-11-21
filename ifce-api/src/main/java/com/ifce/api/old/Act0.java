package com.ifce.api.old;

import com.ifce.api.old.main.object.Inventory0;
import com.ifce.api.old.main.object.Room0;

import java.io.IOException;

public interface Act0 {
    boolean apply(Room0 room0, Inventory0 inventory0) throws IOException, ClassNotFoundException;
}