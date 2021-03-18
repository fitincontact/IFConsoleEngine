package com.fitincontact.engine.main.object;

import com.fitincontact.engine.api.Act;
import com.fitincontact.engine.api.Use;
import com.fitincontact.engine.main.format.Format;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

    private static final long serialVersionUID = -3514284914219542231L;
    private final String word;
    private final boolean isForInventory;
    private final String invName;
    private final String roomDescription;
    private final String actRoomTxt;
    private final String actInventoryTxt;
    private final String useTxt;
    private boolean isInInventory;
    private Act act;
    private Use use;

    protected Item(
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

    public Act getActItem() {
        return act;
    }

    public void setActItem(final Act act) {
        this.act = act;
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

    public Act getAct() {
        return act;
    }

    public void setAct(final Act act) {
        this.act = act;
    }

    public Use getUse() {
        return use;
    }

    public void add(final Use use) {
        this.use = use;
    }

    public void add(final Act act) {
        this.act = act;
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
            final Room room,
            final Inventory inventory
    ) throws IOException, ClassNotFoundException {
        final var print = new ArrayList<String>();
        if (act == null) {
            if (isForInventory && !isInInventory) {
                move(room, inventory);
                print.add(actRoomTxt);
                print.addAll(room.toStrRoom(inventory));
            } else if (isForInventory && isInInventory) {
                print.add(actInventoryTxt);
            } else if (!isForInventory) {
                print.add(actRoomTxt);
            }
        } else {
            act.apply(room, inventory);
        }
        return print;
    }

    public String use(
            final Room room,
            final List<Item> items
    ) {
        if (use == null) {
            return useTxt;
        } else {
            use.apply(room, items);
            return Format.EMPTY;
        }
    }

    public boolean move(
            final Room room,
            final Inventory inventory
    ) {
        if (room.remove(this) && this.isForInventory) {
            inventory.add(this);
            this.isInInventory = true;
            return true;
        }
        return false;
    }

    public void move(
            final Room roomFrom,
            final Room roomTo
    ) {
        roomFrom.remove(this);
        roomTo.getItems().add(this);
    }

    public void drop(
            final Room room,
            final Inventory inventory
    ) {
        room.getItems().add(this);
        inventory.remove(this);
    }

}