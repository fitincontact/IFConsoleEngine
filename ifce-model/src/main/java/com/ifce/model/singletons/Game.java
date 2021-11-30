package com.ifce.model.singletons;

import com.ifce.format.Format;
import com.ifce.model.main.Item;
import com.ifce.model.main.Room;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Class for general info (main state for game engine)
 */
@Data
@Component
public class Game {
    /**
     * Format
     */
    private final Format format;
    /**
     * All game objects
     */
    private final Objects objects;

    private Item player;
    private String annotation = "";
    private Room currentRoom;
}