package com.ifce.model.etc;

import com.ifce.format.Format;
import com.ifce.model.Item;
import com.ifce.model.Room;
import com.ifce.model.singletons.Objects;
import com.ifce.util.Print;
import lombok.Data;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * General state
 */
@Data
@Component
public class Game {
    private final Format format;
    private final Objects objects;

    private Item player;
    private String annotation = "";

    private Room currentRoom;
    private Word word;

    private boolean isEnd;

    public void end(String message) {
        isEnd = true;
        Print.pl(message);
    }

    /**
     * Return room where the item is placed
     *
     * @return room
     */
    public Room getItemRoom(final Item item) {
        Room room = null;
        val rooms = new ArrayList<>(objects.getRooms().values());
        for (val r : rooms) {
            if (r.contains(item)) {
                room = r;
            }
        }
        return room;
    }
}