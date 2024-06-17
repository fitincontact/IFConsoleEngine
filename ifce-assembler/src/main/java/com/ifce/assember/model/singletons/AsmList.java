package com.ifce.assember.model.singletons;

import com.ifce.assember.model.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * All assembling objects for {@AssemblerHandlerService} and real objects
 */
@Data
@Service
public class AsmList {
    private final DialogAsmList dialogAsmList;
    private final DoorAsmList doorAsmList;
    private final ItemAsmList itemAsmList;
    private final PlayerAsmList playerAsmList;
    private final RoomAsmList roomAsmList;
    private final GameAsm gameAsm;
    private final Objects objects;

    public void add(DialogAsm asm){
        dialogAsmList.add(asm);
    }

    public void add(DoorAsm asm){
        doorAsmList.add(asm);
    }

    public void add(ItemAsm asm){
        itemAsmList.add(asm);
    }

    public void add(PlayerAsm asm){
        playerAsmList.add(asm);
    }

    public void add(RoomAsm asm){
        roomAsmList.add(asm);
    }

    public void setAnnotation(String annotation){
        gameAsm.setAnnotation(annotation);
    }

    public List<String> getPlayerNames(){
        return gameAsm.getPlayerNames();
    }
}