package com.ifce.assember.model.singletons;

import com.ifce.model.singletons.Game;
import com.ifce.model.singletons.Objects;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * All assembling objects for {@AssemblerHandlerService} and real objects
 */
@Data
@Service
public class AsmList {
    private final DialogAsmList dialogAsmList;
    private final DoorAsmList doorAsmList;
    private final ItemAsmList itemAsmList;
    private final RoomAsmList roomAsmList;
    private final GameAsm gameAsm;
    private final Objects objects;
    private final Game game;

    public Game getGame(){
        return this.game;
    }
}