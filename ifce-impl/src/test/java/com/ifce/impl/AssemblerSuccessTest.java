package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.assember.AssemblerImpl;
import com.ifce.format.Format;
import com.ifce.model.asm.singletons.*;
import com.ifce.model.etc.Game;
import com.ifce.model.singletons.Objects;
import com.ifce.service.Assembler;
import com.ifce.service.EngineService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AssemblerSuccessTest {
    private final static String ANNOTATION = "ANNOTATION";
    private final static String ITEM_1 = "ITEM_1";
    private final static String ITEM_2 = "ITEM_2";
    private final static String ROOM_1 = "ROOM_1";
    private final static String ROOM_2 = "ROOM_2";
    private final static String DOOR = "DOOR";
    private final static String ERROR_HEAD = "Assembler RuntimeException: ";

    private IFCEService ifceService;

    private DialogAsmList dialogAsmList;
    private DoorAsmList doorAsmList;
    private ItemAsmList itemAsmList;
    private RoomAsmList roomAsmList;
    private Objects objects;
    private GameAsm gameAsm;
    private Game game;
    @Mock
    private Format format;

    private Assembler assembler;
    //todo initUseCase
    @Mock
    private EngineService engineService;

    @BeforeEach
    void initUseCase() {
        dialogAsmList = new DialogAsmList();
        itemAsmList = new ItemAsmList();
        roomAsmList = new RoomAsmList();
        doorAsmList = new DoorAsmList();
        objects = new Objects();
        gameAsm = new GameAsm();
        game = new Game(format, objects);

        assembler = new AssemblerImpl(
                dialogAsmList,
                doorAsmList,
                itemAsmList,
                roomAsmList,
                objects,
                gameAsm,
                game
        );

        ifceService = new IFCEServiceImpl(
                dialogAsmList,
                doorAsmList,
                itemAsmList,
                roomAsmList,
                gameAsm,
                assembler,
                engineService
        );
    }

    @Test
    public void storyTest() {
        ifceService.story(ITEM_1, ANNOTATION);
        ifceService.item(ITEM_1, ROOM_1);
        val door = ifceService.door(DOOR, ROOM_1, ROOM_2);
        ifceService.room(ROOM_1);
        ifceService.room(ROOM_2);
        val item2 = ifceService.item(ITEM_2, ITEM_1);
        ifceService.start();

        Assertions.assertEquals(
                game.getAnnotation(),
                ANNOTATION
        );
        Assertions.assertTrue(game.getObjects().isExistsItem(ITEM_1));
        Assertions.assertTrue(game.getObjects().isExistsItem(ITEM_2));
        Assertions.assertTrue(game.getObjects().isExistsRoom(ROOM_1));
        Assertions.assertTrue(game.getObjects().isExistsRoom(ROOM_2));
        Assertions.assertEquals(
                game.getCurrentRoom().getName().getWord(),
                ROOM_1
        );
        Assertions.assertEquals(
                game.getPlayer().getName().getWord(),
                ITEM_1
        );
        Assertions.assertTrue(game.getPlayer().getInventory().contains(item2));
        Assertions.assertTrue(game.getObjects().getRoom(ROOM_1).getDoors().contains(door));


        int k = 1;

    }
}