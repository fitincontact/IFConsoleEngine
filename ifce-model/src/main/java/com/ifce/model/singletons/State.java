package com.ifce.model.singletons;

import com.ifce.model.main.Door;
import com.ifce.model.main.Item;
import com.ifce.model.main.Room;
import com.ifce.model.main.Word;
import com.ifce.model.main.enums.WordCountType;
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
     * Console raw word
     */
    private Word word;
    /**
     * Current room
     */
    private Room roomCurrent;
    /**
     * Console split words (just not defined)
     */
    private List<String> words = new ArrayList<>();
    /**
     * Defined items
     */
    private List<Item> items = new ArrayList<>();
    /**
     * Defined door
     */
    private Door door;
    /**
     * If complited for finalizer
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
        words.clear();
    }

    public void init() {
        roomCurrent = game.getCurrentRoom();
        player = game.getPlayer();
    }
}