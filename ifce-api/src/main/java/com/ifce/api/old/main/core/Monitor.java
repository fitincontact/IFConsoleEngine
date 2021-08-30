package com.ifce.api.old.main.core;

import com.ifce.api.old.main.enums.EffectType;
import com.ifce.api.old.main.history.RoomHistory1;
import com.ifce.api.old.main.object.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Monitor implements Serializable {

    private static final long serialVersionUID = -2028574438713832452L;

    private static Monitor instance;
    private final RoomHistory1 roomHistory1 = RoomHistory1.getInstance();
    private Way1 way1Go;
    private Item1 roomItem1Act;
    private Item1 invItem1Act;
    private List<Item1> itemsUse = new ArrayList<>();
    private Way1 way1Use;
    private EffectType effectType;
    private Room1 room1Current;
    private Inventory1 inventory1Current;
    private Dialog1 dialog1Current;
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
        way1Go = null;
        roomItem1Act = null;
        invItem1Act = null;
        itemsUse = new ArrayList<>();
        effectType = EffectType.EMPTY;
        victory = false;
    }

    public Way1 getWayGo() {
        return way1Go;
    }

    public void setWayGo(final Way1 way1Go) {
        this.way1Go = way1Go;
    }

    public Item1 getRoomItemAct() {
        return roomItem1Act;
    }

    public void setRoomItemAct(final Item1 roomItem1Act) {
        this.roomItem1Act = roomItem1Act;
    }

    public Item1 getInvItemAct() {
        return invItem1Act;
    }

    public void setInvItemAct(final Item1 invItem1Act) {
        this.invItem1Act = invItem1Act;
    }

    public List<Item1> getItemsUse() {
        return itemsUse;
    }

    public void setItemsUse(final List<Item1> itemsUse) {
        this.itemsUse = itemsUse;
    }

    public Way1 getWayUse() {
        return way1Use;
    }

    public void setWayUse(final Way1 way1Use) {
        this.way1Use = way1Use;
    }

    public EffectType getActType() {
        return effectType;
    }

    public void setActType(final EffectType effectType) {
        this.effectType = effectType;
    }

    public Room1 getRoomCurrent() {
        return room1Current;
    }

    public void setRoomCurrent(final Room1 room1Current) {
        roomHistory1.push(room1Current);
        this.room1Current = room1Current;
    }

    public Inventory1 getInventoryCurrent() {
        return inventory1Current;
    }

    public void setInventoryCurrent(final Inventory1 inventory1Current) {
        this.inventory1Current = inventory1Current;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(final boolean victory) {
        this.victory = victory;
    }

    public Dialog1 getDialogCurrent() {
        return dialog1Current;
    }

    public void setDialogCurrent(final Dialog1 dialog1Current) {
        this.dialog1Current = dialog1Current;
    }

    public List<String> toStrRoomCurrent() {
        return room1Current.toStrRoom(inventory1Current);
    }

    @Override
    public String toString() {
        return room1Current.roomAndInventoryString(inventory1Current);
    }

    public void add(final Item1 item1) {
        itemsUse.add(item1);
    }

    List<Room1> getRooms() {
        final var roomSet = new HashSet<Room1>();
        roomSet.add(room1Current);
        addRoomFromWays(roomSet, room1Current);
        return new ArrayList<>(roomSet);
    }

    void addRoomFromWays(final Set<Room1> room1Set, final Room1 room1) {
        room1.getWays().forEach(w -> {
            room1Set.add(w.getRoom());
            addRoomFromWays(room1Set, w.getRoom());
        });
    }

    public void set(final Monitor m) {
        way1Go = m.way1Go;
        roomItem1Act = m.roomItem1Act;
        invItem1Act = m.invItem1Act;
        itemsUse = m.itemsUse;
        way1Use = m.way1Use;
        effectType = m.effectType;
        room1Current = m.room1Current;
        inventory1Current = m.inventory1Current;
        victory = m.victory;
    }

}