package com.ifce.model.etc;

import com.ifce.format.Format;
import com.ifce.model.Item;
import com.ifce.model.Room;
import com.ifce.model.singleton.Rooms;
import com.ifce.util.Print;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * General state
 */
@Component
@Data
public class Game {
    private final Print print;
    private final Format format;
    private final Rooms rooms;
    private final String annotation;
    private String word;
    private Item player;
    private Room currentRoom;


    private boolean isWin;

}