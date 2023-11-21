package com.ifce.api.old.main.object;

import com.ifce.api.old.Act0;
import com.ifce.api.old.Use0;
import com.ifce.api.old.main.format.Format0;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item0 implements Serializable {

    private static final long serialVersionUID = -3514284914219542231L;
    private final String word;
    private final boolean isForInventory;
    private final String invName;
    private final String roomDescription;
    private final String actRoomTxt;
    private final String actInventoryTxt;
    private final String useTxt;
    private boolean isInInventory;
    private Act0 act0;
    private Use0 use0;

    protected Item0(
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

    public Act0 getActItem() {
        return act0;
    }

    public void setActItem(final Act0 act0) {
        this.act0 = act0;
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

    public Act0 getAct() {
        return act0;
    }

    public void setAct(final Act0 act0) {
        this.act0 = act0;
    }

    public Use0 getUse() {
        return use0;
    }

    public void add(final Use0 use0) {
        this.use0 = use0;
    }

    public void add(final Act0 act0) {
        this.act0 = act0;
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
            final Room0 room0,
            final Inventory0 inventory0
    ) throws IOException, ClassNotFoundException {
        final var print = new ArrayList<String>();
        if (act0 == null) {
            if (isForInventory && !isInInventory) {
                move(room0, inventory0);
                print.add(actRoomTxt);
                print.addAll(room0.toStrRoom(inventory0));
            } else if (isForInventory && isInInventory) {
                print.add(actInventoryTxt);
            } else if (!isForInventory) {
                print.add(actRoomTxt);
            }
        } else {
            act0.apply(room0, inventory0);
        }
        return print;
    }

    public String use(
            final Room0 room0,
            final List<Item0> item0s
    ) {
        if (use0 == null) {
            return useTxt;
        } else {
            use0.apply(room0, item0s);
            return Format0.EMPTY;
        }
    }

    public boolean move(
            final Room0 room0,
            final Inventory0 inventory0
    ) {
        if (room0.remove(this) && this.isForInventory) {
            inventory0.add(this);
            this.isInInventory = true;
            return true;
        }
        return false;
    }

    public void move(
            final Room0 room0From,
            final Room0 room0To
    ) {
        room0From.remove(this);
        room0To.getItems().add(this);
    }

    public void drop(
            final Room0 room0,
            final Inventory0 inventory0
    ) {
        room0.getItems().add(this);
        inventory0.remove(this);
    }

}