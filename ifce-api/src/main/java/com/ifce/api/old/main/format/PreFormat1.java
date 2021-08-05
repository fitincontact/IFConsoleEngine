package com.ifce.api.old.main.format;

//import javafx.util.Pair;
//import jdk.javadoc.internal.doclets.toolkit.util.Utils;

import com.ifce.api.old.main.variable.Command;

public class PreFormat1 {
    private String gameName = "examples";
    private String saveName = "save";
    private String gameSaveMsg = "game save";
    private String gameLoadMsg = "game load";
    private String filenameExtension = ".ser";
    private String roomTitleHead = ":";//RTH
    private String roomDescriptionHead = ":";//RDH
    private String roomItemsHead = ":";//ItH
    private String roomItemSplit = " ";
    private String roomWaySplit = " | ";
    private String itemRoomDescription = " ";
    private String itemInvName = ", ";
    private String inventoryHead = ":";//InH
    private String inventoryItemSplit = ", ";
    private String wayHead = ":";//Wh
    private String consoleHead = "> ";
    private String useSplitSymbol = "-";
    private String goTxt = "transition to the ";
    private String enterTxt = "I can't enter here";
    private String exitTxt = "I can't get out of here";
    private String unDefininedWordIfContains = " - it cannot be used";
    private String unDefininedWordIfNotContains = " - I do not see anything similar here";
    private String unDefininedWordUse = " - need to try something different";
    private Command flagFinish = new Command("f.", "Finish");
    private Command flagItems = new Command("it.", "Items:");
    private Command flagInventory = new Command("i.", "Inventory:");
    private Command flagRoom = new Command("r.", "Room:");
    private String flagSave = "s.";
    private String flagLoad = "l.";
    private String flagSaveList = "sl.";

    protected PreFormat1() {
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

    public Command getFlagFinish() {
        return flagFinish;
    }

    public void setFlagFinish(final Command flagFinish) {
        this.flagFinish = flagFinish;
    }

    public Command getFlagItems() {
        return flagItems;
    }

    public void setFlagItems(final Command flagItems) {
        this.flagItems = flagItems;
    }

    public Command getFlagInventory() {
        return flagInventory;
    }

    public void setFlagInventory(final Command flagInventory) {
        this.flagInventory = flagInventory;
    }

    public Command getFlagRoom() {
        return flagRoom;
    }

    public void setFlagRoom(final Command flagRoom) {
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