package com.fitincontact.engine.main.format;

import javafx.util.Pair;

public class Format {
    public static final String EMPTY = "";

    private static Format instance;

    private final String roomTitleHead;
    private final String roomDescriptionHead;
    private final String roomItemsHead;
    private final String roomItemSplit;
    private final String roomWaySplit;
    private final String itemRoomDescription;
    private final String itemInvName;
    private final String inventoryHead;
    private final String inventoryItemSplit;
    private final String wayHead;
    private final String consoleHead;
    private final String useSplitSymbol;
    private final String goTxt;
    private final String enterTxt;
    private final String exitTxt;
    private final String unDefininedWordIfContains;
    private final String unDefininedWordIfNotContains;
    private final String unDefininedWordUse;
    private final Pair<String, String> flagFinish;
    private final Pair<String, String> flagItems;
    private final Pair<String, String> flagInventory;
    private final Pair<String, String> flagRoom;

    protected Format(
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
            final Pair<String, String> flagFinish,
            final Pair<String, String> flagItems,
            final Pair<String, String> flagInventory,
            final Pair<String, String> flagRoom
    ) {
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
    }

    protected static int setInstance(final PreFormat preFormat) {
        if (instance == null) {
            instance = new Format(
                    preFormat.getRoomTitleHead(),
                    preFormat.getRoomDescriptionHead(),
                    preFormat.getRoomItemsHead(),
                    preFormat.getRoomItemSplit(),
                    preFormat.getRoomWaySplit(),
                    preFormat.getItemRoomDescription(),
                    preFormat.getItemInvName(),
                    preFormat.getInventoryHead(),
                    preFormat.getInventoryItemSplit(),
                    preFormat.getWayHead(),
                    preFormat.getConsoleHead(),
                    preFormat.getUseSplitSymbol(),
                    preFormat.getGoTxt(),
                    preFormat.getEnterTxt(),
                    preFormat.getExitTxt(),
                    preFormat.getUnDefininedWordIfContains(),
                    preFormat.getUnDefininedWordIfNotContains(),
                    preFormat.getUnDefininedWordUse(),
                    preFormat.getFlagFinish(),
                    preFormat.getFlagItems(),
                    preFormat.getFlagInventory(),
                    preFormat.getFlagRoom()
            );
            return 1;
        } else {
            return 0;
        }
    }

    public static Format getInstance() {
        if (instance == null) {
            final PreFormat preFormat = new PreFormat();
            setInstance(preFormat);
        }
        return instance;
    }

    public String getWayHead() {
        return wayHead;
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

    public Pair<String, String> getFlagFinish() {
        return flagFinish;
    }

    public Pair<String, String> getFlagItems() {
        return flagItems;
    }

    public Pair<String, String> getFlagInventory() {
        return flagInventory;
    }

    public Pair<String, String> getFlagRoom() {
        return flagRoom;
    }
}