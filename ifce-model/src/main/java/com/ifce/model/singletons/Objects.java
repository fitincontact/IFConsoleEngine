package com.ifce.model.singletons;

import com.ifce.model.Dialog;
import com.ifce.model.Item;
import com.ifce.model.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class Objects {
    /**
     * unique name on room
     */
    private final Map<String, Room> rooms = new HashMap<>();
    /**
     * unique name on item
     */
    private final Map<String, Item> items = new HashMap<>();
    /**
     * unique name on door
     */
    private final Map<String, Door> doors = new HashMap<>();
    /**
     * unique name on door
     */
    private final Map<String, Dialog> dialogs = new HashMap<>();

    public boolean isExistsRoom(String asmName) {
        return rooms.get(asmName) != null;
    }
    public boolean isExistsItem(String asmName) {
        return items.get(asmName) != null;
    }
    public boolean isExistsDoor(String asmName) {
        return doors.get(asmName) != null;
    }
    public boolean isExistsDialog(String asmName) {
        return dialogs.get(asmName) != null;
    }

    public void add(Room room) {
        rooms.put(room.getAsm().getName(), room);
    }

    public void add(Item item) {
        items.put(item.getAsm().getName(), item);
    }

    public void add(Door door) {
        doors.put(door.getAsm().getName(), door);
    }

    public void add(Dialog dialog) {
        dialogs.put(dialog.getAsm().getName(), dialog);
    }
}