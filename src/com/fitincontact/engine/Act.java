package com.fitincontact.engine;

import javafx.util.Pair;

import static com.fitincontact.engine.Utils.*;

public class Act {
    private Way way;
    private Item roomItem;
    private Item invItem;
    private Pair<Item,Item> useItem;
    private ActType actType;
    private Room room;
    private  Inventory inventory;
    private  boolean victory;

    public Way getWay() {
        return way;
    }

    public void setWay(Way way) {
        this.way = way;
    }

    public Item getRoomItem() {
        return roomItem;
    }

    public void setRoomItem(Item roomItem) {
        this.roomItem = roomItem;
    }

    public Item getInvItem() {
        return invItem;
    }

    public void setInvItem(Item invItem) {
        this.invItem = invItem;
    }

    public Pair<Item, Item> getUseItem() {
        return useItem;
    }

    public void setUseItem(Pair<Item, Item> useItem) {
        this.useItem = useItem;
    }

    public ActType getActType() {
        return actType;
    }

    public void setActType(ActType actType) {
        this.actType = actType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public void pc() {
        room.pr();
        inventory.pi();
        //p("\n");
    }
}
