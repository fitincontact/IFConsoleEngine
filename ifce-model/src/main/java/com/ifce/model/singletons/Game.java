package com.ifce.model.singletons;

import com.ifce.format.Format;
import com.ifce.model.common.Item;
import com.ifce.model.common.Room;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * Current player
     */
    private Item player;
    /**
     * Other players
     */
    private List<Item> players;
    private String annotation = "";
    private Room currentRoom;
}