package com.ifce.impl;

import com.ifce.api.IFCEService;
import com.ifce.assember.AssemblerServiceImpl;
import com.ifce.assember.assemblerHandler.AssemblerHandlerService;
import com.ifce.assember.assemblerHandler.handlers.*;
import com.ifce.assember.model.singletons.*;
import com.ifce.format.Format;
import com.ifce.model.singletons.Game;
import com.ifce.model.singletons.Objects;
import com.ifce.model.singletons.State;
import com.ifce.service.AssemblerService;
import com.ifce.service.EngineService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//todo check Objects.objectTypes in all handlers
@ExtendWith(MockitoExtension.class)
public class AssemblerSuccessTest {
    private final static String ANNOTATION = "ANNOTATION";
    private final static String ITEM_1 = "ITEM_1";
    private final static String ITEM_2 = "ITEM_2";
    private final static String ITEM_3 = "ITEM_3";
    private final static String ROOM_1 = "ROOM_1";
    private final static String ROOM_2 = "ROOM_2";
    private final static String DOOR = "DOOR";
    private final static String ERROR_HEAD = "Assembler RuntimeException: ";

    private IFCEService ifceService;

    private AsmList asmList;

    private DialogAsmList dialogAsmList;
    private DoorAsmList doorAsmList;
    private ItemAsmList itemAsmList;
    private RoomAsmList roomAsmList;
    private GameAsm gameAsm;
    private Objects objects;

    private Game game;
    @Mock
    private Format format;

    private AssemblerService assemblerService;
    private AssemblerHandlerService assemblerHandlerService;

    private ValidateHandler validateHandler;
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

    private State state;

    @BeforeEach
    void initUseCase() {
        dialogAsmList = new DialogAsmList();
        itemAsmList = new ItemAsmList();
        roomAsmList = new RoomAsmList();
        doorAsmList = new DoorAsmList();
        objects = new Objects();
        gameAsm = new GameAsm();

        asmList = new AsmList(dialogAsmList, doorAsmList, itemAsmList, roomAsmList, gameAsm, objects);
        game = new Game(format, asmList.getObjects());

        validateHandler = new ValidateHandler(asmList);
        addingRoomsHandler = new AddingRoomsHandler(asmList);
        addingItemsHandler = new AddingItemsHandler(asmList);
        addingDoorsHandler = new AddingDoorsHandler(asmList);
        addingDialogsHandler = new AddingDialogsHandler(asmList);
        bindingItemsHandler = new BindingItemsHandler(asmList);
        bindingDoorsHandler = new BindingDoorsHandler(asmList);
        bindingDialogsHandler = new BindingDialogsHandler(asmList);
        gameProcessHandler = new GameProcessHandler(asmList, game);

        assemblerHandlerService = new AssemblerHandlerService(
                validateHandler,
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
        state = new State(game);

        ifceService = new IFCEServiceImpl(
                asmList,
                assemblerService,
                engineService,
                state
        );
    }

    @Test
    public void itemPlaceTest() {
        ifceService.story(ITEM_1, ANNOTATION);
        ifceService.item(ITEM_1, ROOM_1);
        ifceService.item(ITEM_2, ROOM_1);
        ifceService.item(ITEM_3, ROOM_2);
        var room1 = ifceService.room(ROOM_1);
        var room2 = ifceService.room(ROOM_2);
        ifceService.start();

        Assertions.assertEquals(
                game.getAnnotation(),
                ANNOTATION
        );
        Assertions.assertTrue(game.getObjects().isExistsItem(ITEM_1));
        Assertions.assertTrue(game.getObjects().isExistsItem(ITEM_2));
        Assertions.assertTrue(game.getObjects().isExistsItem(ITEM_3));
        Assertions.assertTrue(game.getObjects().isExistsRoom(ROOM_1));
        Assertions.assertTrue(game.getObjects().isExistsRoom(ROOM_2));
        Assertions.assertEquals(
                game.getCurrentRoom().getName(),
                ROOM_1
        );
        Assertions.assertEquals(
                game.getPlayer().getName(),
                ITEM_1
        );
        //Assertions.assertTrue(game.getPlayer().getInventory().contains(item2));
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
                game.getCurrentRoom().getName(),
                ROOM_1
        );
        Assertions.assertEquals(
                game.getPlayer().getName(),
                ITEM_1
        );
        Assertions.assertTrue(game.getPlayer().getInventory().contains(item2));
        Assertions.assertTrue(game.getObjects().getRoom(ROOM_1).getDoors().contains(door));
    }
}