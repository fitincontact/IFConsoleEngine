package com.fitincontact.engine.main.core;

import com.fitincontact.engine.main.enums.ActType;
import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Item;
import com.fitincontact.engine.main.object.Room;
import com.fitincontact.engine.main.object.Way;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Act {
    private Way wayGo;
    private Item roomItemAct;
    private Item invItemAct;
    private List<Item> itemsUse = new ArrayList<>();
    private ActType actType;
    private Room roomCurrent;
    private Inventory inventoryCurrent;
    private boolean victory;

    void cleanGoActUse() {
        wayGo = null;
        roomItemAct = null;
        invItemAct = null;
        itemsUse = new ArrayList<>();
        actType = ActType.UNKNOWN;
        //roomPrint = null;
        //inventoryPrint = null;
        victory = false;
    }

    public Way getWayGo() {
        return wayGo;
    }

    public void setWayGo(Way wayGo) {
        this.wayGo = wayGo;
    }

    public Item getRoomItemAct() {
        return roomItemAct;
    }

    public void setRoomItemAct(Item roomItemAct) {
        this.roomItemAct = roomItemAct;
    }

    public Item getInvItemAct() {
        return invItemAct;
    }

    public void setInvItemAct(Item invItemAct) {
        this.invItemAct = invItemAct;
    }

    public List<Item> getItemsUse() {
        return itemsUse;
    }

    public void setItemsUse(List<Item> itemsUse) {
        this.itemsUse = itemsUse;
    }

    public ActType getActType() {
        return actType;
    }

    public void setActType(ActType actType) {
        this.actType = actType;
    }

    public Room getRoomCurrent() {
        return roomCurrent;
    }

    public void setRoomCurrent(Room roomCurrent) {
        this.roomCurrent = roomCurrent;
    }

    public Inventory getInventoryCurrent() {
        return inventoryCurrent;
    }

    public void setInventoryCurrent(Inventory inventoryCurrent) {
        this.inventoryCurrent = inventoryCurrent;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public void pa() {
        roomCurrent.pr();
        inventoryCurrent.pi();
    }

    public void add(Item item) {
        itemsUse.add(item);
    }

    protected void use(
            Room room,
            List<Item> items
    ) {
        items.forEach(i -> i.use(room, items));
    }

    List<Room> getRooms() {
        Set<Room> roomSet = new HashSet<Room>();
        roomSet.add(roomCurrent);
        addRoomFromWays(roomSet, roomCurrent);
        return new ArrayList<>(roomSet);
    }

    void addRoomFromWays(Set<Room> roomSet, Room room) {
        room.getWays().forEach(w -> {
            roomSet.add(w.getRoom());
            addRoomFromWays(roomSet, w.getRoom());
        });
    }

}
