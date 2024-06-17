package com.ifce.assember.model;

import com.ifce.assember.assemblerHandler.AssemblerHandlerService;
import com.api.model.common.Door;
import lombok.Data;

/**
 * Assembling date for doors
 * Use in {@link AssemblerHandlerService} for assembling
 */
@Data
public class DoorAsm {
    /**
     * Map to {@link Objects#doors}
     */
    @SuppressWarnings("JavadocReference")
    private final String name;
    /**
     * Connect to room where this door is placed
     * {@link Objects#rooms}
     */
    @SuppressWarnings("JavadocReference")
    private final String roomFrom;
    /**
     * Connect to room which this door can open
     * {@link Objects#rooms}
     */
    @SuppressWarnings("JavadocReference")
    private final String roomTo;
    /**
     * General game object for game engine
     */
    private final Door door;

    public DoorAsm(
            String name,
            String roomFrom,
            String roomTo
    ) {
        this.name = name;
        this.roomFrom = roomFrom;
        this.roomTo = roomTo;
        door = new Door(name);
    }
}