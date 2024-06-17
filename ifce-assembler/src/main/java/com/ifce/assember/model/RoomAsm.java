package com.ifce.assember.model;

import com.ifce.assember.assemblerHandler.AssemblerHandlerService;
import com.api.model.common.Room;
import lombok.Data;

/**
 * Assembling date for rooms
 * Use in {@link AssemblerHandlerService} for assembling
 */
@Data
public class RoomAsm {
    /**
     * Map to {@link Objects#rooms}
     */
    @SuppressWarnings("JavadocReference")
    private final String name;
    /**
     * General game object for game engine
     */
    private final Room room;

    public RoomAsm(final String name) {
        this.name = name;
        room = new Room(name);
    }
}