package com.ifce.model.etc;

import com.ifce.format.Format;
import com.ifce.model.Item;
import com.ifce.model.Room;
import com.ifce.model.singletons.Objects;
import com.ifce.util.Print;
import lombok.Data;
import org.springframework.stereotype.Component;

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
}