package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.assember.AssemblerImpl;
import com.ifce.format.Format;
import com.ifce.model.asm.singletons.*;
import com.ifce.model.etc.Game;
import com.ifce.model.singletons.Objects;
import com.ifce.service.Assembler;
import com.ifce.service.EngineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IFCEServiceTest {
    private final static String ITEM = "John";
    private final static String ROOM = "yard";
    private final static String DOOR = "door";
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

        assembler = new AssemblerImpl(dialogAsmList, doorAsmList, itemAsmList, roomAsmList, objects, gameAsm, game);

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
    public void doubleRoomTest() {
        ifceService.room(ROOM);
        ifceService.room(ROOM);
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                String.format(ERROR_HEAD + "Assembler.addRooms: There is duplicate room name [%s]", ROOM),
                msg
        );
    }

    @Test
    public void doubleItemTest() {
        ifceService.item(ITEM, "yard");
        ifceService.item(ITEM, "yard2");
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                String.format(ERROR_HEAD + "Assembler.addItems: There is duplicate item name [%s]", ITEM),
                msg
        );
    }

    @Test
    public void doubleDoorTest() {
        ifceService.door(DOOR, "yard");
        ifceService.door(DOOR, "yard2");
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                String.format(ERROR_HEAD + "Assembler.addDoors: There is duplicate door name [%s]", DOOR),
                msg
        );
    }

    @Test
    public void bindingItemsTest() {
        ifceService.item(ITEM, ROOM);
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                String.format(ERROR_HEAD + "Assembler.bindingItems: For item name [%s] not found room name [%s]", ITEM, ROOM),
                msg
        );
    }

    @Test
    public void bindingDoorsTest() {
        ifceService.door(DOOR, ROOM);
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                String.format(ERROR_HEAD + "Assembler.bindingDoors: For door name [%s] not found room name [%s]", DOOR, ROOM),
                msg
        );
    }

    @Test
    public void gameProcessTest() {
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                ERROR_HEAD + "Assembler.gameProcess: Player is not created",
                msg
        );
    }

//    @ParameterizedTest
//    @MethodSource("sumTestData")
//    public void story(int a, int b, int expectedSum) {
//        Assertions.assertEquals(expectedSum, a + b);
//        val hero = ifceService.item("Аруй", "двор");
//        val hero2 = ifceService.item("Аруй", "двор");
//        //System.out.println(hero);
//        ifceService.start();
//    }
//    public static Object[][] sumTestData() {
//        return new Object[][]{
//                {2, 2, 4},
//                {10, 1, 11},
//                {1000000, -1000000, 0}
//        };
//    }
}