package com.ifce.api.old.main.object;

import com.ifce.api.old.Act1;
import com.ifce.api.old.Use1;
import com.ifce.api.old.main.format.Format1;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item1 implements Serializable {

    private static final long serialVersionUID = -3514284914219542231L;
    private final String word;
    private final boolean isForInventory;
    private final String invName;
    private final String roomDescription;
    private final String actRoomTxt;
    private final String actInventoryTxt;
    private final String useTxt;
    private boolean isInInventory;
    private Act1 act1;
    private Use1 use1;

    protected Item1(
            final String word,
            final boolean isForInventory,
            final String invName,
            final String roomDescription,
            final String actText,
            final String actInventoryTxt,
            final String useTxt
    ) {
        this.word = word;
        this.isForInventory = isForInventory;
        this.invName = invName;
        this.roomDescription = roomDescription;
        this.actRoomTxt = actText;
        this.actInventoryTxt = actInventoryTxt;
        this.useTxt = useTxt;
    }

    public Act1 getActItem() {
        return act1;
    }

    public void setActItem(final Act1 act1) {
        this.act1 = act1;
    }

    public String getWord() {
        return word;
    }

    public String getInvName() {
        return invName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public String getActRoomTxt() {
        return actRoomTxt;
    }

    public String getActInventoryTxt() {
        return actInventoryTxt;
    }

    public String getUseTxt() {
        return useTxt;
    }

    public Act1 getAct() {
        return act1;
    }

    public void setAct(final Act1 act1) {
        this.act1 = act1;
    }

    public Use1 getUse() {
        return use1;
    }

    public void add(final Use1 use1) {
        this.use1 = use1;
    }

    public void add(final Act1 act1) {
        this.act1 = act1;
    }

    public boolean isForInventory() {
        return isForInventory;
    }

    public boolean isInInventory() {
        return isInInventory;
    }

    public void setInInventory(final boolean inInventory) {
        isInInventory = inInventory;
    }

    @Override
    public String toString() {
        return word;
    }

    public List<String> act(
            final Room1 room1,
            final Inventory1 inventory1
    ) throws IOException, ClassNotFoundException {
        final var print = new ArrayList<String>();
        if (act1 == null) {
            if (isForInventory && !isInInventory) {
                move(room1, inventory1);
                print.add(actRoomTxt);
                print.addAll(room1.toStrRoom(inventory1));
            } else if (isForInventory && isInInventory) {
                print.add(actInventoryTxt);
            } else if (!isForInventory) {
                print.add(actRoomTxt);
            }
        } else {
            act1.apply(room1, inventory1);
        }
        return print;
    }

    public String use(
            final Room1 room1,
            final List<Item1> item1s
    ) {
        if (use1 == null) {
            return useTxt;
        } else {
            use1.apply(room1, item1s);
            return Format1.EMPTY;
        }
    }

    public boolean move(
            final Room1 room1,
            final Inventory1 inventory1
    ) {
        if (room1.remove(this) && this.isForInventory) {
            inventory1.add(this);
            this.isInInventory = true;
            return true;
        }
        return false;
    }

    public void move(
            final Room1 room1From,
            final Room1 room1To
    ) {
        room1From.remove(this);
        room1To.getItems().add(this);
    }

    public void drop(
            final Room1 room1,
            final Inventory1 inventory1
    ) {
        room1.getItems().add(this);
        inventory1.remove(this);
    }

}