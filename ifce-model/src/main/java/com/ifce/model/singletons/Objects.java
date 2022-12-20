package com.ifce.model.singletons;

import com.ifce.model.main.*;
import com.ifce.model.main.enums.ObjectType;
import lombok.Data;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * All game objects for assembling {@link com.ifce.service.AssemblerService}
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
     * unique asm name on dialog
     */
    private final Map<String, Dialog> dialogs = new HashMap<>();
    /**
     * unique asm name on all game types (everyone object has unique name {@see} {@link ObjectAbstract#name})
     */
    @SuppressWarnings("JavadocReference")
    private final Map<String, ObjectType> objectTypes = new HashMap<>();

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

    public boolean isExists(String asmName) {
        return objectTypes.get(asmName) != null;
    }

    public void add(final Room room) {
        val word = room.getName();
        rooms.put(word, room);
        objectTypes.put(word, ObjectType.ROOM);
    }

    public void add(Item item) {
        val word = item.getName();
        items.put(word, item);
        objectTypes.put(word, ObjectType.ITEM);
    }

    public void add(final Door door) {
        val word = door.getName();
        doors.put(word, door);
        objectTypes.put(word, ObjectType.DOOR);
    }

    public void add(final Dialog dialog) {
        val word = dialog.getName();
        dialogs.put(word, dialog);
        objectTypes.put(word, ObjectType.DIALOG);
    }

    public Room getRoom(final String name) {
        return rooms.get(name);
    }

    public Item getItem(final String name) {
        return items.get(name);
    }

    /**
     * Return room where the item is placed
     *
     * @param item item
     * @return room
     */
    public Room getItemRoom(final Item item) {
        Room room = null;
        val rooms = new ArrayList<>(getRooms().values());
        for (val r : rooms) {
            if (r.contains(item)) {
                room = r;
            }
        }
        return room;
    }

    public ObjectType defineObjectType(final String word) {
        return objectTypes.get(word);
    }

}