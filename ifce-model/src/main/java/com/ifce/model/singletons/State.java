package com.ifce.model.singletons;

import com.ifce.model.common.Door;
import com.ifce.model.common.Item;
import com.ifce.model.common.Room;
import com.ifce.model.common.Words;
import com.ifce.model.common.enums.WordCountType;
import com.ifce.util.Print;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * General state
 */
@Data
@Component
public class State {
    private final Game game;
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
        roomCurrent = game.getCurrentRoom();
        player = game.getPlayer();
    }
}