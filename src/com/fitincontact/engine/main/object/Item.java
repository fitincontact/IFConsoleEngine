package com.fitincontact.engine.main.object;

import com.fitincontact.engine.api.ActItem;

import java.util.List;

import static com.fitincontact.engine.Utils.p;
import static com.fitincontact.engine.Utils.pl;

public class Item {
    private final String word;
    private final boolean isForInventory;
    //if "" then invisible
    private final String invName;
    private final String roomDescription;
    private final String actRoomTxt;
    private final String actInventoryTxt;
    private final String useTxt;
    public ActItem actItem;

    protected Item(
            String word,
            boolean isForInventory,
            String invName,
            String roomDescription,
            String actText,
            String takeText,
            String useText
    ) {
        this.word = word;
        this.isForInventory = isForInventory;
        this.invName = invName;
        this.roomDescription = roomDescription;
        this.actRoomTxt = actText;
        this.actInventoryTxt = takeText;
        this.useTxt = useText;
    }

    public ActItem getActItem() {
        return actItem;
    }

    public void setActItem(ActItem actItem) {
        this.actItem = actItem;
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

    public boolean isForInventory() {
        return isForInventory;
    }

    public void pd() {
        p(roomDescription + " ");
    }

    public void pn() {
        pl(invName + ", ");
    }

    public String act(
            final Room room,
            final Inventory inventory
    ) {
        if (actItem == null) {
            pl(actRoomTxt);
            if (isForInventory) {
                move(room, inventory);
                inventory.pi();
            }

            return actRoomTxt;
        }

        actItem.apply(room, inventory);

        return "actItem!=null";

    }

    public void move(
            final Room room,
            final Inventory inventory
    ) {
        room.remove(this);
        inventory.add(this);
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

    public String use(Room room, List<Item> items) {
        p("");
        return "";
    }

}
