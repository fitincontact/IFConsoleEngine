package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.assember.AssemblerImpl;
import com.ifce.format.Format;
import com.ifce.model.asm.singletons.DialogAsmList;
import com.ifce.model.asm.singletons.DoorAsmList;
import com.ifce.model.asm.singletons.ItemAsmList;
import com.ifce.model.asm.singletons.RoomAsmList;
import com.ifce.model.etc.Game;
import com.ifce.model.singletons.Objects;
import com.ifce.service.Assembler;
import com.ifce.service.EngineService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IFCEServiceTest {
    private IFCEService ifceService;

    @Mock
    private DialogAsmList dialogAsmList;
    @Mock
    private DoorAsmList doorAsmList;
    //@Mock
    private ItemAsmList itemAsmList;
    @Mock
    private RoomAsmList roomAsmList;
    //@Mock
    private Objects objects;
    //@Mock
    private Game game;
    @Mock
    private Format format;

    private Assembler assembler;
    //todo initUseCase
    @Mock
    private EngineService engineService;

    @BeforeEach
    void initUseCase() {
        itemAsmList = new ItemAsmList();
        objects = new Objects();
        game = new Game(format, objects);

        assembler = new AssemblerImpl(dialogAsmList, doorAsmList, itemAsmList, roomAsmList, objects, game);

        ifceService = new IFCEServiceImpl(
                dialogAsmList,
                doorAsmList,
                itemAsmList,
                roomAsmList,
                game,
                assembler,
                engineService
        );
    }

    @Test
    public void story() {
        val hero = ifceService.item("Аруй", "двор");
        val hero2 = ifceService.item("Аруй", "двор");
        System.out.println(hero);


        ifceService.start();
    }
}