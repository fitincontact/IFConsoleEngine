package com.fitincontact.engine.main.format;

import javafx.util.Pair;

public class PreFormat {
    private String gameName = "game";
    private String saveName = "save";
    private String gameSaveMsg = "game save";
    private String gameLoadMsg = "game load";
    private String filenameExtension = ".ser";
    private String roomTitleHead = "RTH:";
    private String roomDescriptionHead = "RDH:";
    private String roomItemsHead = "ItH:";
    private String roomItemSplit = " ";
    private String roomWaySplit = " | ";
    private String itemRoomDescription = " ";
    private String itemInvName = ", ";
    private String inventoryHead = "InH:";
    private String inventoryItemSplit = ", ";
    private String wayHead = "Wh:";
    private String consoleHead = ">";
    private String useSplitSymbol = "-";
    private String goTxt = "transition to the ";
    private String enterTxt = "I can't enter here";
    private String exitTxt = "I can't get out of here";
    private String unDefininedWordIfContains = " - it cannot be used";
    private String unDefininedWordIfNotContains = " - I do not see anything similar here";
    private String unDefininedWordUse = " - need to try something different";
    private Pair<String, String> flagFinish = new Pair<>("f.", "Finish");
    private Pair<String, String> flagItems = new Pair<>("it.", "Items:");
    private Pair<String, String> flagInventory = new Pair<>("i.", "Inventory:");
    private Pair<String, String> flagRoom = new Pair<>("r.", "Room:");
    private String flagSave = "s.";
    private String flagLoad = "l.";
    private String flagSaveList = "sl.";

    protected PreFormat() {
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(final String gameName) {
        this.gameName = gameName;
    }

    public String getRoomTitleHead() {
        return roomTitleHead;
    }

    public void setRoomTitleHead(final String roomTitleHead) {
        this.roomTitleHead = roomTitleHead;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(final String saveName) {
        this.saveName = saveName;
    }

    public String getGameSaveMsg() {
        return gameSaveMsg;
    }

    public void setGameSaveMsg(final String gameSaveMsg) {
        this.gameSaveMsg = gameSaveMsg;
    }

    public String getGameLoadMsg() {
        return gameLoadMsg;
    }

    public void setGameLoadMsg(final String gameLoadMsg) {
        this.gameLoadMsg = gameLoadMsg;
    }

    public String getFilenameExtension() {
        return filenameExtension;
    }

    public void setFilenameExtension(final String filenameExtension) {
        this.filenameExtension = filenameExtension;
    }

    public String getRoomDescriptionHead() {
        return roomDescriptionHead;
    }

    public void setRoomDescriptionHead(final String roomDescriptionHead) {
        this.roomDescriptionHead = roomDescriptionHead;
    }

    public String getRoomItemsHead() {
        return roomItemsHead;
    }

    public void setRoomItemsHead(final String roomItemsHead) {
        this.roomItemsHead = roomItemsHead;
    }

    public String getRoomItemSplit() {
        return roomItemSplit;
    }

    public void setRoomItemSplit(final String roomItemSplit) {
        this.roomItemSplit = roomItemSplit;
    }

    public String getRoomWaySplit() {
        return roomWaySplit;
    }

    public void setRoomWaySplit(final String roomWaySplit) {
        this.roomWaySplit = roomWaySplit;
    }

    public String getItemRoomDescription() {
        return itemRoomDescription;
    }

    public void setItemRoomDescription(final String itemRoomDescription) {
        this.itemRoomDescription = itemRoomDescription;
    }

    public String getItemInvName() {
        return itemInvName;
    }

    public void setItemInvName(final String itemInvName) {
        this.itemInvName = itemInvName;
    }

    public String getInventoryHead() {
        return inventoryHead;
    }

    public void setInventoryHead(final String inventoryHead) {
        this.inventoryHead = inventoryHead;
    }

    public String getInventoryItemSplit() {
        return inventoryItemSplit;
    }

    public void setInventoryItemSplit(final String inventoryItemSplit) {
        this.inventoryItemSplit = inventoryItemSplit;
    }

    public String getWayHead() {
        return wayHead;
    }

    public void setWayHead(final String wayHead) {
        this.wayHead = wayHead;
    }

    public String getConsoleHead() {
        return consoleHead;
    }

    public void setConsoleHead(final String consoleHead) {
        this.consoleHead = consoleHead;
    }

    public String getUseSplitSymbol() {
        return useSplitSymbol;
    }

    public void setUseSplitSymbol(final String useSplitSymbol) {
        this.useSplitSymbol = useSplitSymbol;
    }

    public String getGoTxt() {
        return goTxt;
    }

    public void setGoTxt(final String goTxt) {
        this.goTxt = goTxt;
    }

    public String getEnterTxt() {
        return enterTxt;
    }

    public void setEnterTxt(final String enterTxt) {
        this.enterTxt = enterTxt;
    }

    public String getExitTxt() {
        return exitTxt;
    }

    public void setExitTxt(final String exitTxt) {
        this.exitTxt = exitTxt;
    }

    public String getUnDefininedWordIfContains() {
        return unDefininedWordIfContains;
    }

    public void setUnDefininedWordIfContains(final String unDefininedWordIfContains) {
        this.unDefininedWordIfContains = unDefininedWordIfContains;
    }

    public String getUnDefininedWordIfNotContains() {
        return unDefininedWordIfNotContains;
    }

    public void setUnDefininedWordIfNotContains(final String unDefininedWordIfNotContains) {
        this.unDefininedWordIfNotContains = unDefininedWordIfNotContains;
    }

    public String getUnDefininedWordUse() {
        return unDefininedWordUse;
    }

    public void setUnDefininedWordUse(final String unDefininedWordUse) {
        this.unDefininedWordUse = unDefininedWordUse;
    }

    public Pair<String, String> getFlagFinish() {
        return flagFinish;
    }

    public void setFlagFinish(final Pair<String, String> flagFinish) {
        this.flagFinish = flagFinish;
    }

    public Pair<String, String> getFlagItems() {
        return flagItems;
    }

    public void setFlagItems(final Pair<String, String> flagItems) {
        this.flagItems = flagItems;
    }

    public Pair<String, String> getFlagInventory() {
        return flagInventory;
    }

    public void setFlagInventory(final Pair<String, String> flagInventory) {
        this.flagInventory = flagInventory;
    }

    public Pair<String, String> getFlagRoom() {
        return flagRoom;
    }

    public void setFlagRoom(final Pair<String, String> flagRoom) {
        this.flagRoom = flagRoom;
    }

    public String getFlagSave() {
        return flagSave;
    }

    public void setFlagSave(String flagSave) {
        this.flagSave = flagSave;
    }

    public String getFlagLoad() {
        return flagLoad;
    }

    public void setFlagLoad(String flagLoad) {
        this.flagLoad = flagLoad;
    }

    public String getFlagSaveList() {
        return flagSaveList;
    }

    public void setFlagSaveList(String flagSaveList) {
        this.flagSaveList = flagSaveList;
    }
}