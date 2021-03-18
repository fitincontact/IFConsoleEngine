package com.fitincontact.engine.main.core;

import com.fitincontact.engine.main.enums.EffectType;
import com.fitincontact.engine.main.history.RoomHistory;
import com.fitincontact.engine.main.object.*;

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
    private EffectType effectType;
    private Room roomCurrent;
    private Inventory inventoryCurrent;
    private Dialog dialogCurrent;
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
        effectType = EffectType.EMPTY;
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

    public EffectType getActType() {
        return effectType;
    }

    public void setActType(final EffectType effectType) {
        this.effectType = effectType;
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

    public Dialog getDialogCurrent() {
        return dialogCurrent;
    }

    public void setDialogCurrent(final Dialog dialogCurrent) {
        this.dialogCurrent = dialogCurrent;
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
        final var roomSet = new HashSet<Room>();
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
        wayGo = m.wayGo;
        roomItemAct = m.roomItemAct;
        invItemAct = m.invItemAct;
        itemsUse = m.itemsUse;
        wayUse = m.wayUse;
        effectType = m.effectType;
        roomCurrent = m.roomCurrent;
        inventoryCurrent = m.inventoryCurrent;
        victory = m.victory;
    }

}