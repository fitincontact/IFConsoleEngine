package com.ifce.model.singletons;

import com.ifce.model.main.Dialog;
import com.ifce.model.main.Door;
import com.ifce.model.main.Item;
import com.ifce.model.main.Room;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * All game objects for assembling com.ifce.service.AssemblerService
 */
@Data
@Component
public class Objects {
    /**
     * unique asm name on room
     */
    private final Map<String, Room> rooms = new HashMap<>();
    /**
     * unique asm name on item
     */
    private final Map<String, Item> items = new HashMap<>();
    /**
     * unique asm name on door
     */
    private final Map<String, Door> doors = new HashMap<>();
    /**
     * unique asm name on door
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
        rooms.put(room.getName(), room);
    }

    public void add(Item item) {
        items.put(item.getName(), item);
    }

    public void add(Door door) {
        doors.put(door.getName(), door);
    }

    public void add(Dialog dialog) {
        dialogs.put(dialog.getName(), dialog);
    }

    public Room getRoom(final String name) {
        return rooms.get(name);
    }

    public Item getItem(String playerName) {
        return items.get(playerName);
    }
}