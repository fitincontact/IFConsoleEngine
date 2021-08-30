package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.assember.AssemblerServiceImpl;
import com.ifce.assember.assemblerHandler.AssemblerHandlerService;
import com.ifce.assember.assemblerHandler.handlers.*;
import com.ifce.assember.model.singletons.*;
import com.ifce.format.Format;
import com.ifce.model.singletons.Game;
import com.ifce.model.singletons.Objects;
import com.ifce.service.AssemblerService;
import com.ifce.service.EngineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AssebmlerFailTest {
    private final static String ITEM = "ITEM";
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


    private AssemblerService assemblerService;
    private AssemblerHandlerService assemblerHandlerService;

    private AddingRoomsHandler addingRoomsHandler;
    private AddingItemsHandler addingItemsHandler;
    private AddingDoorsHandler addingDoorsHandler;
    private AddingDialogsHandler addingDialogsHandler;
    private BindingItemsHandler bindingItemsHandler;
    private BindingDoorsHandler bindingDoorsHandler;
    private BindingDialogsHandler bindingDialogsHandler;
    private GameProcessHandler gameProcessHandler;

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


        addingRoomsHandler = new AddingRoomsHandler(objects, roomAsmList);
        addingItemsHandler = new AddingItemsHandler(objects, itemAsmList);
        addingDoorsHandler = new AddingDoorsHandler(objects, doorAsmList);
        addingDialogsHandler = new AddingDialogsHandler(objects, dialogAsmList);
        bindingItemsHandler = new BindingItemsHandler(objects, itemAsmList);
        bindingDoorsHandler = new BindingDoorsHandler(objects, doorAsmList);
        bindingDialogsHandler = new BindingDialogsHandler();
        gameProcessHandler = new GameProcessHandler(objects, gameAsm, game);

        assemblerHandlerService = new AssemblerHandlerService(
                addingRoomsHandler,
                addingItemsHandler,
                addingDoorsHandler,
                addingDialogsHandler,
                bindingItemsHandler,
                bindingDoorsHandler,
                bindingDialogsHandler,
                gameProcessHandler
        );
        assemblerService = new AssemblerServiceImpl(assemblerHandlerService);

        ifceService = new IFCEServiceImpl(
                dialogAsmList,
                doorAsmList,
                itemAsmList,
                roomAsmList,
                gameAsm,
                assemblerService,
                engineService
        );
    }

    @Test
    public void doubleRoomTest() {
        ifceService.room(ROOM_1);
        ifceService.room(ROOM_1);
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                String.format(ERROR_HEAD + "Assembler.addRooms: There is duplicate room name [%s]", ROOM_1),
                msg
        );
    }

    @Test
    public void doubleItemTest() {
        ifceService.item(ITEM, ROOM_1);
        ifceService.item(ITEM, ROOM_2);
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
        ifceService.door(DOOR, ROOM_1, ROOM_2);
        ifceService.door(DOOR, ROOM_1, ROOM_2);
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
        ifceService.item(ITEM, ROOM_1);
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                String.format(ERROR_HEAD + "Assembler.bindingItems: For item name [%s] not found room name [%s]\n" +
                                "Assembler.bindingItems: For item name [%s] not found item name [%s]",
                        ITEM,
                        ROOM_1,
                        ITEM,
                        ROOM_1
                ),
                msg
        );
    }

    @Test
    public void bindingDoorsTest1() {
        ifceService.door(DOOR, ROOM_1, ROOM_2);
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                String.format(ERROR_HEAD + "Assembler.bindingDoors: For door name [%s] not found roomTo name [%s]", DOOR, ROOM_2),
                msg
        );
    }

    @Test
    public void bindingDoorsTest2() {
        ifceService.room(ROOM_2);
        ifceService.door(DOOR, ROOM_1, ROOM_2);
        var msg = "";
        try {
            ifceService.start();
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        Assertions.assertEquals(
                String.format(ERROR_HEAD + "Assembler.bindingDoors: For door name [%s] not found roomFrom name [%s]", DOOR, ROOM_1),
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