package com.fitincontact.engine.main.object;

import com.fitincontact.engine.api.Act;
import com.fitincontact.engine.api.Use;
import com.fitincontact.engine.main.format.Format;

import java.util.List;

import static com.fitincontact.engine.main.utils.Utils.p;
import static com.fitincontact.engine.main.utils.Utils.pl;

public class Item {
    private final Format format = Format.getInstance();

    private final String word;
    private final boolean isForInventory;
    //if Format.EMPTY then invisible
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
            final String useTxt,
            final Act act,
            final Use use
    ) {
        this.word = word;
        this.isForInventory = isForInventory;
        this.invName = invName;
        this.roomDescription = roomDescription;
        this.actRoomTxt = actText;
        this.actInventoryTxt = actInventoryTxt;
        this.useTxt = useTxt;
        this.act = act;
        this.use = use;
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

    public boolean isForInventory() {
        return isForInventory;
    }

    public boolean isInInventory() {
        return isInInventory;
    }

    public void setInInventory(final boolean inInventory) {
        isInInventory = inInventory;
    }

    public void pd() {
        p(roomDescription + format.getItemRoomDescription());
    }

    public void pi() {
        pl(invName + format.getItemInvName());
    }

    @Override
    public String toString() {
        return word;
    }

    public void act(
            final Room room,
            final Inventory inventory
    ) {
        if (act == null) {
            if (isForInventory && !isInInventory) {
                //todo check boolean
                move(room, inventory);
                pl(actRoomTxt);
                room.pr(inventory);
            } else if (isForInventory && isInInventory) {
                pl(actInventoryTxt);
            } else if (!isForInventory) {
                pl(actRoomTxt);
            }
        } else {
            act.apply(room, inventory);
        }
    }

    public void use(
            final Room room,
            final List<Item> items
    ) {
        if (use == null) {
            pl(useTxt);
        } else {
            use.apply(room, items);
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