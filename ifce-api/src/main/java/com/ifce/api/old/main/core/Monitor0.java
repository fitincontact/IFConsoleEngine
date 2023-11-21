package com.ifce.api.old.main.core;

import com.ifce.api.old.main.enums.EffectType0;
import com.ifce.api.old.main.object.*;
import com.ifce.api.old.main.history.RoomHistory0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Monitor0 implements Serializable {

    private static final long serialVersionUID = -2028574438713832452L;

    private static Monitor0 instance;
    private final RoomHistory0 roomHistory0 = RoomHistory0.getInstance();
    private Way0 way0Go;
    private Item0 roomItem0Act;
    private Item0 invItem0Act;
    private List<Item0> itemsUse = new ArrayList<>();
    private Way0 way0Use;
    private EffectType0 effectType0;
    private Room0 room0Current;
    private Inventory0 inventory0Current;
    private Dialog0 dialog0Current;
    private boolean victory;

    private Monitor0() {
    }

    public static Monitor0 getInstance() {
        if (instance == null) {
            instance = new Monitor0();
        }
        return instance;
    }

    void cleanGoActUse() {
        way0Go = null;
        roomItem0Act = null;
        invItem0Act = null;
        itemsUse = new ArrayList<>();
        effectType0 = EffectType0.EMPTY;
        victory = false;
    }

    public Way0 getWayGo() {
        return way0Go;
    }

    public void setWayGo(final Way0 way0Go) {
        this.way0Go = way0Go;
    }

    public Item0 getRoomItemAct() {
        return roomItem0Act;
    }

    public void setRoomItemAct(final Item0 roomItem0Act) {
        this.roomItem0Act = roomItem0Act;
    }

    public Item0 getInvItemAct() {
        return invItem0Act;
    }

    public void setInvItemAct(final Item0 invItem0Act) {
        this.invItem0Act = invItem0Act;
    }

    public List<Item0> getItemsUse() {
        return itemsUse;
    }

    public void setItemsUse(final List<Item0> itemsUse) {
        this.itemsUse = itemsUse;
    }

    public Way0 getWayUse() {
        return way0Use;
    }

    public void setWayUse(final Way0 way0Use) {
        this.way0Use = way0Use;
    }

    public EffectType0 getActType() {
        return effectType0;
    }

    public void setActType(final EffectType0 effectType0) {
        this.effectType0 = effectType0;
    }

    public Room0 getRoomCurrent() {
        return room0Current;
    }

    public void setRoomCurrent(final Room0 room0Current) {
        roomHistory0.push(room0Current);
        this.room0Current = room0Current;
    }

    public Inventory0 getInventoryCurrent() {
        return inventory0Current;
    }

    public void setInventoryCurrent(final Inventory0 inventory0Current) {
        this.inventory0Current = inventory0Current;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(final boolean victory) {
        this.victory = victory;
    }

    public Dialog0 getDialogCurrent() {
        return dialog0Current;
    }

    public void setDialogCurrent(final Dialog0 dialog0Current) {
        this.dialog0Current = dialog0Current;
    }

    public List<String> toStrRoomCurrent() {
        return room0Current.toStrRoom(inventory0Current);
    }

    @Override
    public String toString() {
        return room0Current.roomAndInventoryString(inventory0Current);
    }

    public void add(final Item0 item0) {
        itemsUse.add(item0);
    }

    List<Room0> getRooms() {
        final var roomSet = new HashSet<Room0>();
        roomSet.add(room0Current);
        addRoomFromWays(roomSet, room0Current);
        return new ArrayList<>(roomSet);
    }

    void addRoomFromWays(final Set<Room0> room0Set, final Room0 room0) {
        room0.getWays().forEach(w -> {
            room0Set.add(w.getRoom());
            addRoomFromWays(room0Set, w.getRoom());
        });
    }

    public void set(final Monitor0 m) {
        way0Go = m.way0Go;
        roomItem0Act = m.roomItem0Act;
        invItem0Act = m.invItem0Act;
        itemsUse = m.itemsUse;
        way0Use = m.way0Use;
        effectType0 = m.effectType0;
        room0Current = m.room0Current;
        inventory0Current = m.inventory0Current;
        victory = m.victory;
    }

}