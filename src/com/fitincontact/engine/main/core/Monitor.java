package com.fitincontact.engine.main.core;

import com.fitincontact.engine.main.enums.ActType;
import com.fitincontact.engine.main.history.RoomHistory;
import com.fitincontact.engine.main.object.Inventory;
import com.fitincontact.engine.main.object.Item;
import com.fitincontact.engine.main.object.Room;
import com.fitincontact.engine.main.object.Way;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Monitor implements Serializable {

    private static final long serialVersionUID = -2028574438713832452L;

    private static Monitor instance;
    private final RoomHistory roomHistory = RoomHistory.getInstance();
    private Way wayGo;
    private Item roomItemAct;
    private Item invItemAct;
    private List<Item> itemsUse = new ArrayList<>();
    private Way wayUse;
    private ActType actType;
    private Room roomCurrent;
    private Inventory inventoryCurrent;
    private boolean victory;

    private Monitor() {
    }

    public static Monitor getInstance() {
        if (instance == null) {
            instance = new Monitor();
        }
        return instance;
    }

    void cleanGoActUse() {
        wayGo = null;
        roomItemAct = null;
        invItemAct = null;
        itemsUse = new ArrayList<>();
        actType = ActType.EMPTY;
        victory = false;
    }

    public Way getWayGo() {
        return wayGo;
    }

    public void setWayGo(final Way wayGo) {
        this.wayGo = wayGo;
    }

    public Item getRoomItemAct() {
        return roomItemAct;
    }

    public void setRoomItemAct(final Item roomItemAct) {
        this.roomItemAct = roomItemAct;
    }

    public Item getInvItemAct() {
        return invItemAct;
    }

    public void setInvItemAct(final Item invItemAct) {
        this.invItemAct = invItemAct;
    }

    public List<Item> getItemsUse() {
        return itemsUse;
    }

    public void setItemsUse(final List<Item> itemsUse) {
        this.itemsUse = itemsUse;
    }

    public Way getWayUse() {
        return wayUse;
    }

    public void setWayUse(final Way wayUse) {
        this.wayUse = wayUse;
    }

    public ActType getActType() {
        return actType;
    }

    public void setActType(final ActType actType) {
        this.actType = actType;
    }

    public Room getRoomCurrent() {
        return roomCurrent;
    }

    public void setRoomCurrent(final Room roomCurrent) {
        roomHistory.push(roomCurrent);
        this.roomCurrent = roomCurrent;
    }

    public Inventory getInventoryCurrent() {
        return inventoryCurrent;
    }

    public void setInventoryCurrent(final Inventory inventoryCurrent) {
        this.inventoryCurrent = inventoryCurrent;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(final boolean victory) {
        this.victory = victory;
    }

    public List<String> toStrRoomCurrent() {
        return roomCurrent.toStrRoom(inventoryCurrent);
    }

    @Override
    public String toString() {
        return roomCurrent.roomAndInventoryString(inventoryCurrent);
    }

    public void add(final Item item) {
        itemsUse.add(item);
    }

    List<Room> getRooms() {
        final Set<Room> roomSet = new HashSet<Room>();
        roomSet.add(roomCurrent);
        addRoomFromWays(roomSet, roomCurrent);
        return new ArrayList<>(roomSet);
    }

    void addRoomFromWays(final Set<Room> roomSet, final Room room) {
        room.getWays().forEach(w -> {
            roomSet.add(w.getRoom());
            addRoomFromWays(roomSet, w.getRoom());
        });
    }

    public void set(final Monitor m) {
        this.wayGo = m.wayGo;
        this.roomItemAct = m.roomItemAct;
        this.invItemAct = m.invItemAct;
        this.itemsUse = m.itemsUse;
        this.wayUse = m.wayUse;
        this.actType = m.actType;
        this.roomCurrent = m.roomCurrent;
        this.inventoryCurrent = m.inventoryCurrent;
        this.victory = m.victory;
    }

}