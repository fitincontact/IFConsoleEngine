package com.ifce.model.singletons;

import com.ifce.format.Format;
import com.ifce.model.main.Item;
import com.ifce.model.main.Room;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Class for general info
 */
@Data
@Component
public class Game {
    private final Format format;
    private final Objects objects;

    private Item player;
    private String annotation = "";
    private Room currentRoom;
}