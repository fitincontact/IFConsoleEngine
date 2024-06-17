package com.api.model.singletons;

import com.api.model.common.Item;
import com.api.model.common.Player;
import com.api.model.common.Room;
import com.api.model.enums.WordCountType;
import com.ifce.format.Format;

import com.ifce.util.Print;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    private Player player;
    /**
     * Other players
     */
    private List<Item> players;
    private String annotation = "";
    private Room currentRoom;

    /**
     * Player
     */
    private Item player;
    /**
     * Console raw words
     */
    private Words words;
    /**
     * Current room
     */
    private Room roomCurrent;
    /**
     * Console split words (just not defined)
     */
    private List<String> consoleWords = new ArrayList<>();
    /**
     * Defined items
     */
    private List<Item> items = new ArrayList<>();
    /**
     * Defined door
     */
    private Door door;
    /**
     * If completed for finalizer
     */
    private boolean isOmit;
    /**
     * WordCountType
     */
    private WordCountType wordCountType;

    private boolean isEnd;

    public void end(String message) {
        isEnd = true;
        Print.pl(message);
    }

    public void clear() {
        isOmit = false;
        wordCountType = WordCountType.EMPTY;
        consoleWords.clear();
    }

    public void init() {
        roomCurrent = this.getRoomCurrent();
        player = this.getPlayer();
    }
}