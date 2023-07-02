package com.ifce.assember.model;

import com.ifce.model.common.Player;
import com.ifce.model.singletons.Objects;
import lombok.Data;

@Data
public class PlayerAsm {
    /**
     * Map to {@link Objects#items}
     */
    private final String name;
    /**
     * Room or item where this item is placed
     */
    private final String place;
    /**
     * General game object for game engine
     */
    private final Player player;

    public PlayerAsm(
            final String name,
            final String place,
            final Objects objects
    ) {
        this.name = name;
        this.place = place;
        player = new Player(name, objects);
    }
}
