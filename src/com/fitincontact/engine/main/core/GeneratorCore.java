package com.fitincontact.engine.main.core;

import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Person;
import com.fitincontact.engine.main.object.Room;

public class GeneratorCore {
    public Core newCore(
            final Person person,
            final Room room,
            final Inventory inventory
    ){
        return new Core(
        person,
                room,
                inventory
        );
    }
}
