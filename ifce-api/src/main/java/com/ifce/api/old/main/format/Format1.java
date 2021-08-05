package com.ifce.api.old.main.format;

import com.ifce.api.old.main.variable.Command;
//import com.sun.tools.javac.util.Pair;
//import jdk.javadoc.internal.doclets.toolkit.util.Utils;

import java.io.Serializable;

public class Format1 implements Serializable {
    private static final long serialVersionUID = 7009343869686846026L;

    private static Format1 instance;

    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String SPLIT_SPACE = "\\s+";

    private final String saveName;
    private final String gameSaveMsg;
    private final String gameLoadMsg;
    private final String filenameExtension;
    private String gameName;
    private String roomTitleHead;
    private String roomDescriptionHead;
    private String roomItemsHead;
    private String roomItemSplit;
    private String roomWaySplit;
    private String itemRoomDescription;
    private String itemInvName;
    private String inventoryHead;
    private String inventoryItemSplit;
    private String wayHead;
    private String consoleHead;
    private String useSplitSymbol;
    private String goTxt;
    private String enterTxt;
    private String exitTxt;
    private String unDefininedWordIfContains;
    private String unDefininedWordIfNotContains;
    private String unDefininedWordUse;
    private Command flagFinish;
    private Command flagItems;
    private Command flagInventory;
    private Command flagRoom;
    private String flagSave;
    private String flagLoad;
    private String flagSaveList;

    protected Format1(
            final String gameName,
            final String saveName,
            final String gameSaveMsg,
            final String gameLoadMsg,
            final String filenameExtension,
            final String roomTitleHead,
            final String roomDescriptionHead,
            final String roomItemsHead,
            final String roomItemSplit,
            final String roomWaySplit,
            final String itemRoomDescription,
            final String itemInvName,
            final String inventoryHead,
            final String inventoryItemSplit,
            final String wayHead,
            final String consoleHead,
            final String useSplitSymbol,
            final String goTxt,
            final String enterTxt,
            final String exitTxt,
            final String unDefininedWordIfContains,
            final String unDefininedWordIfNotContains,
            final String unDefininedWordUse,
            final Command flagFinish,
            final Command flagItems,
            final Command flagInventory,
            final Command flagRoom,
            final String flagSave,
            final String flagLoad,
            final String flagSaveList
    ) {
        this.gameName = gameName;
        this.saveName = saveName;
        this.gameSaveMsg = gameSaveMsg;
        this.gameLoadMsg = gameLoadMsg;
        this.filenameExtension = filenameExtension;
        this.roomTitleHead = roomTitleHead;
        this.roomDescriptionHead = roomDescriptionHead;
        this.roomItemsHead = roomItemsHead;
        this.roomItemSplit = roomItemSplit;
        this.roomWaySplit = roomWaySplit;
        this.itemRoomDescription = itemRoomDescription;
        this.itemInvName = itemInvName;
        this.inventoryHead = inventoryHead;
        this.inventoryItemSplit = inventoryItemSplit;
        this.wayHead = wayHead;
        this.consoleHead = consoleHead;
        this.useSplitSymbol = useSplitSymbol;
        this.goTxt = goTxt;
        this.enterTxt = enterTxt;
        this.exitTxt = exitTxt;
        this.unDefininedWordIfContains = unDefininedWordIfContains;
        this.unDefininedWordIfNotContains = unDefininedWordIfNotContains;
        this.unDefininedWordUse = unDefininedWordUse;
        this.flagFinish = flagFinish;
        this.flagItems = flagItems;
        this.flagInventory = flagInventory;
        this.flagRoom = flagRoom;
        this.flagSave = flagSave;
        this.flagLoad = flagLoad;
        this.flagSaveList = flagSaveList;
    }

    protected static int setInstance(final PreFormat1 preFormat1) {
        if (instance == null) {
            instance = new Format1(
                    preFormat1.getGameName(),
                    preFormat1.getSaveName(),
                    preFormat1.getGameSaveMsg(),
                    preFormat1.getGameLoadMsg(),
                    preFormat1.getFilenameExtension(),
                    preFormat1.getRoomTitleHead(),
                    preFormat1.getRoomDescriptionHead(),
                    preFormat1.getRoomItemsHead(),
                    preFormat1.getRoomItemSplit(),
                    preFormat1.getRoomWaySplit(),
                    preFormat1.getItemRoomDescription(),
                    preFormat1.getItemInvName(),
                    preFormat1.getInventoryHead(),
                    preFormat1.getInventoryItemSplit(),
                    preFormat1.getWayHead(),
                    preFormat1.getConsoleHead(),
                    preFormat1.getUseSplitSymbol(),
                    preFormat1.getGoTxt(),
                    preFormat1.getEnterTxt(),
                    preFormat1.getExitTxt(),
                    preFormat1.getUnDefininedWordIfContains(),
                    preFormat1.getUnDefininedWordIfNotContains(),
                    preFormat1.getUnDefininedWordUse(),
                    preFormat1.getFlagFinish(),
                    preFormat1.getFlagItems(),
                    preFormat1.getFlagInventory(),
                    preFormat1.getFlagRoom(),
                    preFormat1.getFlagSave(),
                    preFormat1.getFlagLoad(),
                    preFormat1.getFlagSaveList()
            );
            return 1;
        } else {
            return 0;
        }
    }

    public static Format1 getInstance() {
        if (instance == null) {
            final var preFormat = new PreFormat1();
            setInstance(preFormat);
        }
        return instance;
    }

    public String getGameName() {
        return gameName;
    }

    public String getWayHead() {
        return wayHead;
    }

    public String getSaveName() {
        return saveName;
    }

    public String getGameSaveMsg() {
        return gameSaveMsg;
    }

    public String getGameLoadMsg() {
        return gameLoadMsg;
    }

    public String getFilenameExtension() {
        return filenameExtension;
    }

    public String getRoomTitleHead() {
        return roomTitleHead;
    }

    public String getRoomDescriptionHead() {
        return roomDescriptionHead;
    }

    public String getRoomItemsHead() {
        return roomItemsHead;
    }

    public String getRoomItemSplit() {
        return roomItemSplit;
    }

    public String getRoomWaySplit() {
        return roomWaySplit;
    }

    public String getInventoryHead() {
        return inventoryHead;
    }

    public String getConsoleHead() {
        return consoleHead;
    }

    public String getUseSplitSymbol() {
        return useSplitSymbol;
    }

    public String getUnDefininedWordIfContains() {
        return unDefininedWordIfContains;
    }

    public String getUnDefininedWordIfNotContains() {
        return unDefininedWordIfNotContains;
    }

    public String getUnDefininedWordUse() {
        return unDefininedWordUse;
    }

    public String getItemRoomDescription() {
        return itemRoomDescription;
    }

    public String getItemInvName() {
        return itemInvName;
    }

    public String getInventoryItemSplit() {
        return inventoryItemSplit;
    }

    public String getGoTxt() {
        return goTxt;
    }

    public String getEnterTxt() {
        return enterTxt;
    }

    public String getExitTxt() {
        return exitTxt;
    }

    public Command getFlagFinish() {
        return flagFinish;
    }

    public Command getFlagItems() {
        return flagItems;
    }

    public Command getFlagInventory() {
        return flagInventory;
    }

    public Command getFlagRoom() {
        return flagRoom;
    }

    public String getFlagSave() {
        return flagSave;
    }

    public String getFlagLoad() {
        return flagLoad;
    }

    public String getFlagSaveList() {
        return flagSaveList;
    }

    public void set(final Format1 f) {
        this.gameName = f.gameName;
        this.roomTitleHead = f.roomTitleHead;
        this.roomDescriptionHead = f.roomDescriptionHead;
        this.roomItemsHead = f.roomItemsHead;
        this.roomItemSplit = f.roomItemSplit;
        this.roomWaySplit = f.roomWaySplit;
        this.itemRoomDescription = f.itemRoomDescription;
        this.itemInvName = f.itemInvName;
        this.inventoryHead = f.inventoryHead;
        this.inventoryItemSplit = f.inventoryItemSplit;
        this.wayHead = f.wayHead;
        this.consoleHead = f.consoleHead;
        this.useSplitSymbol = f.useSplitSymbol;
        this.goTxt = f.goTxt;
        this.enterTxt = f.enterTxt;
        this.exitTxt = f.exitTxt;
        this.unDefininedWordIfContains = f.unDefininedWordIfContains;
        this.unDefininedWordIfNotContains = f.unDefininedWordIfNotContains;
        this.unDefininedWordUse = f.unDefininedWordUse;
        this.flagFinish = f.flagFinish;
        this.flagItems = f.flagItems;
        this.flagInventory = f.flagInventory;
        this.flagRoom = f.flagRoom;
        this.flagSave = f.flagSave;
        this.flagLoad = f.flagLoad;
        this.flagSaveList = f.flagSaveList;
    }
}