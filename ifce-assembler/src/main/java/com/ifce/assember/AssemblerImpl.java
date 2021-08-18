package com.ifce.assember;

import com.ifce.model.asm.singletons.DialogAsmList;
import com.ifce.model.asm.singletons.DoorAsmList;
import com.ifce.model.asm.singletons.ItemAsmList;
import com.ifce.model.asm.singletons.RoomAsmList;
import com.ifce.model.etc.Game;
import com.ifce.service.Assembler;
import com.ifce.util.Print;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AssemblerImpl implements Assembler {
    private final DialogAsmList dialogAsmList;
    private final DoorAsmList doorAsmList;
    private final ItemAsmList itemAsmList;
    private final RoomAsmList roomAsmList;
    private final Game game;

    public void assemble() {
        Print.pl("assemble");
    }
}