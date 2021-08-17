package com.ifce.model.etc;

import com.ifce.format.Format;
import com.ifce.model.Item;
import com.ifce.model.Room;
import com.ifce.model.singletons.Rooms;
import com.ifce.util.Print;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * General state
 */
@Data
@Component
public class Game {
    private final Print print;
    private final Format format;
    private final Rooms rooms;

    private Item player;
    private Room currentRoom;
    private Word word;
    private String annotation;

    private boolean isWin;

    public void end(String message) {
        isWin = true;
        Print.pl(message);
    }
}